import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object Day17_Task1 {

  def main(args: Array[String]): Unit = {
    val spark = createSparkSession()

    val customersDF = loadCSV(spark, "/Users/ronak/Downloads/Customers.csv")
    val productsDF = loadCSV(spark, "/Users/ronak/Downloads/Products.csv")
    val salesDF = loadCSV(spark, "/Users/ronak/Downloads/Sales.csv")

    val resultsDF = processSalesData(salesDF, productsDF, customersDF)

    resultsDF.show()

    saveToCassandra(resultsDF, "mykeyspaceronak", "SalesCFamily")

    spark.close()
  }

  def createSparkSession(): SparkSession = {
    SparkSession.builder
      .appName("JoinTablesToKeySpaces")
      .master("local[*]")
      .config("spark.cassandra.connection.host", "cassandra.eu-north-1.amazonaws.com")
      .config("spark.cassandra.connection.port", "9142")
      .config("spark.cassandra.connection.ssl.enabled", "true")
      .config("spark.cassandra.auth.username", "**********")
      .config("spark.cassandra.auth.password", "**********")
      .config("spark.cassandra.input.consistency.level", "LOCAL_QUORUM")
      .config("spark.cassandra.connection.ssl.trustStore.path", "/Users/ronak/Downloads/Cass/cassandra_truststore.jks")
      .config("spark.cassandra.connection.ssl.trustStore.password", "Ron@1999")
      .getOrCreate()
  }

  def loadCSV(spark: SparkSession, path: String): DataFrame = {
    spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(path)
  }

  def processSalesData(salesDF: DataFrame, productsDF: DataFrame, customersDF: DataFrame): DataFrame = {
    val salesProductDF = salesDF
      .join(productsDF, salesDF("product_id") === productsDF("product_id"))
      .select(
        salesDF("transaction_id"),
        salesDF("product_id"),
        productsDF("name").as("productname"),
        salesDF("customer_id"),
        productsDF("price"),
        salesDF("units")
      )

    val fullJoinDF = salesProductDF
      .join(customersDF, salesProductDF("customer_id") === customersDF("customer_id"))
      .select(
        col("transaction_id"),
        col("product_id"),
        col("productname"),
        customersDF("name").as("customername"),
        col("price"),
        col("units")
      )

    fullJoinDF.withColumn("salesamount", col("price") * col("units"))
      .select(
        col("transaction_id"),
        col("product_id"),
        col("productname"),
        col("customername"),
        col("salesamount")
      )
  }

  def saveToCassandra(df: DataFrame, keyspace: String, table: String): Unit = {
    df.write
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> "SalesCFamily", "keyspace" -> "mykeyspaceronak"))
      .mode("append")
      .save()
  }
}
