import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import java.util.Properties

object SparkTask15 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("CSV to JSON Converter")
      .getOrCreate()

    val inputPath = "hdfs:///employees.csv"
    val outputPath = "hdfs:///employees_op"

    val df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(inputPath)

    val filteredDf = df.filter(col("department") === "Marketing")

    filteredDf.write
      .mode("overwrite")
      .json(outputPath)

    val jdbcUrl = "jdbc:mysql://34.94.143.214:3306/company"
    val connectionProperties = new Properties()
    connectionProperties.put("user", "admin")
    connectionProperties.put("password", "Ron@1999")
    connectionProperties.put("driver", "com.mysql.cj.jdbc.Driver")

    filteredDf.write
      .mode("overwrite")
      .jdbc(jdbcUrl, "department_salaries", connectionProperties)

    spark.stop()
  }
}
