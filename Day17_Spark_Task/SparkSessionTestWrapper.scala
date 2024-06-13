import org.apache.spark.sql.SparkSession

object SparkSessionTestWrapper {
  lazy val spark: SparkSession = {
    SparkSession.builder
      .appName("Test")
      .master("local[2]")
      .getOrCreate()
  }
}
