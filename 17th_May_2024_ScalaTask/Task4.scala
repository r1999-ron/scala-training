import scala.io.Source

case class EmployeeDetails(sno:Int, name:String, city:String, salary:Double, department:String)

object EmployeeAnalysis {
    def main(args: Array[String]) : Unit={

    val filename = "/Users/ronak/training/Day4/employees.csv"
    val employees: List[EmployeeDetails] = {
        val lines = Source.fromFile(filename).getLines().drop(1).toList
        lines.map { line =>
            val Array(sno, name, city, salary, department) = line.split(",").map(_.trim)
            EmployeeDetails(sno.toInt, name, city, salary.toDouble, department)
        }
    }

    //Filtering operations
    val highSalaryEmployees = employees.filter(_.salary>60000)
    val marketingEmployees = employees.filter(_.department == "Marketing")

    //Map operation to produce formatted report
    val formattedReport = employees.map{ e=>
    s"${e.name} worked in ${e.department} department and earns ${e.salary}."}

    val departmentWiseStats = employees.groupBy(_.department).map { case (department, empList) =>
        val totalSalary = empList.map(_.salary).sum
        val avgSalary = totalSalary / empList.size
        (department, (totalSalary, avgSalary, empList.size))
    }

    println("High Salary Employees (> 60000):")
    highSalaryEmployees.foreach(println)

    println("\nEmployees in Marketing Department:")
    marketingEmployees.foreach(println)

    println("\nFormatted Report: ")
    formattedReport.foreach(println)

    println("\nDepartment Wise Statistics (Total Salary, Avg Salary, No. of Employees): ")
    departmentWiseStats.foreach { case (department, (totalSalary, avgSalary, numEmployees)) =>
        
        println(s"Department : $department, Total Salary: $totalSalary, Average Salary: $avgSalary, Number of Employees: $numEmployees")
    }
  }
}