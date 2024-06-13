import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfterAll
import Day17_Task1.processSalesData

class Task1SuiteTest extends AnyFunSuite with BeforeAndAfterAll {

  // Use the singleton Spark session
  lazy val spark: SparkSession = SparkSessionTestWrapper.spark

  override def beforeAll(): Unit = {
    // Spark session is initialized lazily, no need to create it here
  }

  override def afterAll(): Unit = {
    spark.stop()
  }

  test("processSalesData should calculate salesamount correctly") {
    import spark.implicits._

    val salesDF = Seq(
      (1, 101, 201, 2),
      (2, 102, 202, 3)
    ).toDF("transaction_id", "product_id", "customer_id", "units")

    val productsDF = Seq(
      (101, "Widget A", 10.99),
      (102, "Widget B", 12.49)
    ).toDF("product_id", "name", "price")

    val customersDF = Seq(
      (201, "John Doe", "123 Elm St", "Springfield"),
      (202, "Jane Smith", "456 Oak St", "Shelbyville")
    ).toDF("customer_id", "name", "address", "city")

    val resultDF = processSalesData(salesDF, productsDF, customersDF)

    val expectedDF = Seq(
      (1, 101, "Widget A", "John Doe", 21.98),
      (2, 102, "Widget B", "Jane Smith", 37.47)
    ).toDF("transaction_id", "product_id", "productname", "customername", "salesamount")

    assert(resultDF.collect() sameElements expectedDF.collect())
  }
}