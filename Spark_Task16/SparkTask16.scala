import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, collect_list, struct}
import java.util.Properties

object SparkTask16 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("EmployeeDepartmentJoin")
      .master("local[*]")
      .getOrCreate()

    val jdbcHostname = "34.94.143.214"
    val jdbcPort = 3306
    val jdbcDatabase = "company"
    val jdbcUrl = s"jdbc:mysql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}"
    val jdbcUsername = "admin"
    val jdbcPassword = "Ron@1999"

    val connectionProperties = new Properties()
    connectionProperties.put("user", jdbcUsername)
    connectionProperties.put("password", jdbcPassword)

    val departmentDF = spark.read
      .jdbc(jdbcUrl, "Department", connectionProperties)


    val employeeDF = spark.read
      .jdbc(jdbcUrl, "Employee", connectionProperties)

    val joinedDF = employeeDF.join(departmentDF, "Dept_no")

    val groupedDF = joinedDF.groupBy("Dept_no", "deptName").agg(
      collect_list(struct(
        col("Eno").as("Eno"),
        col("Name").as("Name"),
        col("Dept_no").as("Dept_no"),
        col("Position").as("Position")
      )).as("Employee")
    )

    val resultDF = groupedDF.select(
      col("Dept_no"),
      col("deptName"),
      col("Employee")
    )

    val resultJson = resultDF.toJSON.collect()

    println("[" + resultJson.mkString(",\n") + "]")

    spark.stop()
  }
}