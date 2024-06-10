import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

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

    spark.stop()
  }
}