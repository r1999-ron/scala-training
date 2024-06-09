import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.functions._
import java.util.Properties

object RDBMSToS3CSV {
  // Define a case class to represent database configuration
  case class DatabaseConfig(url: String, user: String, password: String, driver: String){
    def toProperties : Properties ={
      val properties = new Properties()
      properties.put("user", user)
      properties.put("password", password)
      properties.put("driver", driver)
      properties
    }
  }

  // Function to read database configuration from environment variables
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

  // Function to perform filter operation
  def filterData(df: DataFrame): DataFrame = {
    df.filter(col("job_title") === "Developer")
  }

  // Function to perform aggregation operation
  def aggregateData(df: DataFrame): DataFrame = {
    df.groupBy("job_title").agg(count("*").as("count"))
  }

  // Function to write DataFrame to CSV in S3
  def writeToS3CSV(df: DataFrame, s3Bucket: String, s3Path: String): Unit = {
    df.write.csv(s"s3a://$s3Bucket/$s3Path")
  }

  // Main function
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .appName("RDBMSToS3CSV")
      .master("local[*]")
      .config("spark.hadoop.fs.s3a.aws.credentials.provider", "org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider")
      .getOrCreate()

    try {
      // Read database configuration
      val dbConfig = readDatabaseConfig()

      // Read table from RDBMS
      val df: DataFrame = spark.read
        .jdbc(dbConfig.url, "employee", dbConfig.toProperties)

      // Perform operations on DataFrame
      val filteredDf = filterData(df)
      val aggregatedDf = aggregateData(df)

      // Write filtered/aggregated data to CSV in S3
      writeToS3CSV(filteredDf, "newbucketronak", "filtered_data")
      writeToS3CSV(aggregatedDf, "newbucketronak", "aggregated_data")

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