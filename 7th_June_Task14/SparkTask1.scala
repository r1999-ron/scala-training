import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import java.util.Properties

object JsonToMySQL {

  case class Employee(empId: String, jobTitle: String, firstName: String, lastName: String, fullName: String, address: String, contact: String, email: String)


  case class DatabaseConfig(url: String, user: String, password: String, driver: String)


  def readAWSCredentials(): (String, String) = {
    val awsAccessKeyId = sys.env.getOrElse("AWS_ACCESS_KEY_ID", "")
    val awsSecretAccessKey = sys.env.getOrElse("AWS_SECRET_ACCESS_KEY", "")
    if (awsAccessKeyId.isEmpty || awsSecretAccessKey.isEmpty) {
      throw new IllegalStateException("AWS credentials are not set.")
    }
    (awsAccessKeyId, awsSecretAccessKey)
  }


  def readDatabaseConfig(): DatabaseConfig = {
    val dbUrl = sys.env.getOrElse("DB_URL", "")
    val dbUser = sys.env.getOrElse("DB_USER", "")
    val dbPassword = sys.env.getOrElse("DB_PASSWORD", "")
    val dbDriver = sys.env.getOrElse("DB_DRIVER", "")
    if (dbUrl.isEmpty || dbUser.isEmpty || dbPassword.isEmpty || dbDriver.isEmpty) {
      throw new IllegalStateException("Database details are not set.")
    }
    DatabaseConfig(dbUrl, dbUser, dbPassword, dbDriver)
  }


  def filterEmployees(df: DataFrame): DataFrame = {
    df.withColumn("Employee", explode(col("Employees")))
      .select("Employee.*")
      .filter(col("jobTitle") === "Developer")
  }


  def aggregateEmployees(df: DataFrame): DataFrame = {
    df.withColumn("Employee", explode(col("Employees")))
      .select("Employee.*")
      .groupBy("jobTitle")
      .agg(count("empId").as("count"))
  }


  def writeToMySQL(df: DataFrame, tableName: String, config: DatabaseConfig): Unit = {
    val connectionProperties = new Properties()
    connectionProperties.put("user", config.user)
    connectionProperties.put("password", config.password)
    connectionProperties.put("driver", config.driver)
    df.write.mode("append").jdbc(config.url, tableName, connectionProperties)
  }


  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("Json to MySQL")
      .master("local[*]")
      .config("spark.jars", "/Users/ronak/Downloads/mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar")
      .config("spark.hadoop.fs.s3a.aws.credentials.provider", "org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider")
      .getOrCreate()

    try {

      val (awsAccessKeyId, awsSecretAccessKey) = readAWSCredentials()


      val hadoopConfig = spark.sparkContext.hadoopConfiguration
      hadoopConfig.set("fs.s3a.access.key", awsAccessKeyId)
      hadoopConfig.set("fs.s3a.secret.key", awsSecretAccessKey)
      hadoopConfig.set("fs.s3a.endpoint", "s3.amazonaws.com")
      hadoopConfig.set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

      // Reading JSON file from S3
      val df: DataFrame = spark.read.option("multiline", "true").json("s3a://newbucketronak/employees.json")

      // Filter employees
      val filteredDf = filterEmployees(df)
      filteredDf.show()

      // Aggregate employees
      val aggregatedDf = aggregateEmployees(df)
      aggregatedDf.show()

      // Reading database configuration
      val dbConfig = readDatabaseConfig()

      // Writing filtered data to MySQL
      writeToMySQL(filteredDf, "filtered_employees", dbConfig)

      // Writing aggregated data to MySQL
      writeToMySQL(aggregatedDf, "aggregated_employees", dbConfig)

    } catch {
      case e: Throwable =>
        println(s"An error occurred: ${e.getMessage}")
        e.printStackTrace()
    } finally {
      // Stop Spark Session
      spark.stop()
    }
  }
}