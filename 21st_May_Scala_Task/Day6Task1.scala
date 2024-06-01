import scala.io.Source
import java.sql.{Connection, DriverManager, PreparedStatement, Statement}
import java.util.concurrent.{Callable, Executors, ExecutorService, Future}
import scala.collection.mutable.ListBuffer

case class DepartmentClass(id: Int, name: String)
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
      // Dropping any existing table
      dropTableIfExists(connection, "Employee")
      dropTableIfExists(connection, "Department")
      createTables(connection)

      // Read CSV file and create EmployeeDetails
      val employees: List[EmployeeDetails] = {
        val lines = Source.fromFile(filename).getLines().toList
        val data = lines.tail.map(_.split(",").map(_.trim))
        data.map { case Array(sno, name, city, salary, department) =>
          EmployeeDetails(sno.toInt, name, city, salary.toDouble, department)
        }
      }

      // Extract unique departments and create Department objects manually
      var currentId = 1
      var departments = List[DepartmentClass]()
      var departmentMap = Map[String, Int]()

      employees.foreach { e =>
        if (!departmentMap.contains(e.department)) {
          departmentMap += (e.department -> currentId)
          departments = departments :+ DepartmentClass(currentId, e.department)
          currentId += 1
        }
      }

      insertDepartments(connection, departments)

      // Create Employee objects with department ID reference
      val employeesWithDeptId = employees.map { e =>
        EmployeeClass(e.sno, e.name, e.city, e.salary, departmentMap(e.department))
      }

      // Using ExecutorService for parallel processing
      val executor: ExecutorService = Executors.newFixedThreadPool(10)

      try {
        val futures: List[Future[Unit]] = employeesWithDeptId.map { emp =>
          executor.submit(new Callable[Unit] {
            override def call(): Unit = {
              insertEmployee(connection, emp)
            }
          })
        }

        // Await all futures to complete
        futures.foreach(_.get())
        println("All employees inserted.")
      } finally {
        executor.shutdown()
      }

      printDepartmentsAndEmployees(connection)

    } finally {
      connection.close()
    }
  }

  def createTables(connection: Connection): Unit = {
    val statement = connection.createStatement()
    statement.execute(
      """CREATE TABLE IF NOT EXISTS Department (
        |id INT AUTO_INCREMENT PRIMARY KEY,
        |name VARCHAR(255) NOT NULL
        |)""".stripMargin)
    
    println("Department Table created successfully.")

    statement.execute(
      """CREATE TABLE IF NOT EXISTS Employee (
        |id INT AUTO_INCREMENT PRIMARY KEY,
        |name VARCHAR(255) NOT NULL,
        |city VARCHAR(255) NOT NULL,
        |salary DOUBLE NOT NULL,
        |departmentId INT,
        |threadname VARCHAR(255),
        |timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        |FOREIGN KEY (departmentId) REFERENCES Department(id)
        |)""".stripMargin)


    println("Employee Table created successfully.")

    statement.close()
  }

  def dropTableIfExists(connection: Connection, tableName: String): Unit = {
    val statement = connection.createStatement()
    statement.executeUpdate(s"DROP TABLE IF EXISTS $tableName")
    statement.close()
  }

  def insertDepartments(connection: Connection, departments: List[DepartmentClass]): Unit = {
    val preparedStatement = connection.prepareStatement("INSERT IGNORE INTO Department (id, name) VALUES (?, ?)")
    departments.foreach { dept =>
      preparedStatement.setInt(1, dept.id)
      preparedStatement.setString(2, dept.name)
      preparedStatement.addBatch()
    }
    preparedStatement.executeBatch()
    preparedStatement.close()
  }

  def insertEmployee(connection: Connection, emp: EmployeeClass): Unit = {
    val preparedStatement = connection.prepareStatement("INSERT INTO Employee (id, name, city, salary, departmentId, threadname) VALUES (?, ?, ?, ?, ?, ?)")
    preparedStatement.setInt(1, emp.id)
    preparedStatement.setString(2, emp.name)
    preparedStatement.setString(3, emp.city)
    preparedStatement.setDouble(4, emp.salary)
    preparedStatement.setInt(5, emp.departmentId)
    preparedStatement.setString(6, Thread.currentThread().getName)
    preparedStatement.executeUpdate()
    preparedStatement.close()
  }

  def printDepartmentsAndEmployees(connection: Connection): Unit = {
    val departmentStatement = connection.createStatement()
    val departmentResultSet = departmentStatement.executeQuery("SELECT * FROM Department")

    println("Departments")
    while (departmentResultSet.next()) {
      val deptId = departmentResultSet.getInt("id")
      val deptName = departmentResultSet.getString("name")
      println(s" |- $deptName")

      val employeeStatement = connection.prepareStatement("SELECT * FROM Employee WHERE departmentId = ?")
      employeeStatement.setInt(1, deptId)
      val employeeResultSet = employeeStatement.executeQuery()

      while (employeeResultSet.next()) {
        val empName = employeeResultSet.getString("name")
        val empCity = employeeResultSet.getString("city")
        val empSalary = employeeResultSet.getDouble("salary")
        val empThread = employeeResultSet.getString("threadname")
        val empTimestamp = employeeResultSet.getTimestamp("timestamp")
        println(s"|  |------ $empName")
      }
      employeeResultSet.close()
      employeeStatement.close()
    }
    departmentResultSet.close()
    departmentStatement.close()
  }
}