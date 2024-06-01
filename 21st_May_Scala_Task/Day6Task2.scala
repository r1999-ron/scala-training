import scala.io.Source
import java.sql.{Connection, DriverManager, PreparedStatement, Statement}
import java.util.concurrent.{Callable, Executors, ExecutorService, Future}
import scala.collection.mutable.ListBuffer

case class EmployeeClass(id: Int, name: String, city: String, salary: Double, departmentId: Int)
case class EmployeeDetails(sno: Int, name: String, city: String, salary: Double, department: String)

object EmployeeAnalysis {
  val url = "jdbc:mysql://hadoop-server.mysql.database.azure.com:3306/ronak"
  val username = "sqladmin"
  val password = "Password@12345"

  def main(args: Array[String]): Unit = {
    Class.forName("com.mysql.cj.jdbc.Driver")

    val filename = "/Users/ronak/training/Day5/employees.csv"

    val connection: Connection = DriverManager.getConnection(url, username, password)

    try {
      // Read CSV file and create EmployeeDetails
      val employees: List[EmployeeDetails] = {
        val lines = Source.fromFile(filename).getLines().toList
        val data = lines.tail.map(_.split(",").map(_.trim))
        data.map { case Array(sno, name, city, salary, department) =>
          EmployeeDetails(sno.toInt, name, city, salary.toDouble, department)
        }
      }
      val salesEmployees = getSalesEmployees(connection)
      val combination = generateCombinations(salesEmployees, 4)

      println("The combination of Four members to be sent from Sales Dept to client: " )
      combination.foreach{
        combo => println(combo.map(_.name).mkString(","))
      }

    } finally {
      connection.close()
    }
  }

  def getSalesEmployees(connection: Connection): List[EmployeeClass] = {
  val statement = connection.prepareStatement("SELECT * FROM Employee WHERE departmentId = (SELECT id FROM Department WHERE name = 'Sales')")
  val resultSet = statement.executeQuery()

  var salesEmployees = new ListBuffer[EmployeeClass]()
  while (resultSet.next()) {
    salesEmployees += EmployeeClass(
      resultSet.getInt("id"),
      resultSet.getString("name"),
      resultSet.getString("city"),
      resultSet.getDouble("salary"),
      resultSet.getInt("departmentId")
    )
  }
  resultSet.close()
  statement.close()

  salesEmployees.toList
}
  def generateCombinations(employees : List[EmployeeClass], k: Int): List[List[EmployeeClass]]={
    def backtrack(start: Int, currentComb: List[EmployeeClass]) : List[List[EmployeeClass]]={
        if(currentComb.length == k){
            List(currentComb)
        }else{
            (start until employees.length).flatMap{i =>
               backtrack(i+1, currentComb :+ employees(i)) 
            }.toList
        }
    }
    backtrack(0, List())
  }
}