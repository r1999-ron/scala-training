/*
Create an application
that asks you to enter (sno,name,city,department)
and add the the tuple record in the appropriate deparment 
and print the organization tree
Create a tree data structure of your own
 Note: Do not use map or any other collection

 and for representing (sno,name,city) use tuple 
 and the application must be interactive
 need to stop when you say exit
 
Organization
└── Finance
    ├── Payments
    │   ├── (1,Ravi,Chennai)
    │   ├── (2,Ram,Chennai)
    │   
    │  
    │   
    │   
    │   
    └── Sales
        ├── Marketing
        │   ├── (3,"Rohan","Kolkata")
        │   ├── (4,"RAkesh","Mumbai")
        │ 
        ├── (5,Ravi,Mumbai)
        ├
        ├── Advertisements
        │   ├── (6,Ricky,Chennai)
        │   
        │ 
        │  
        └── SalesManagement
*/

import scala.io.StdIn.readLine

class OrganizationUnit(val name: String){
    def printOrganizationStructure(prefix: String = ""):Unit={
        println(prefix + name)
    }
}
class Employee(sno: Int, name: String, city: String) extends OrganizationUnit(s"(${sno}, ${name}, ${city})")

class Department(name: String) extends OrganizationUnit(name){
  var employees: Array[Employee] = Array()
  var subDepartments: Array[Department] = Array()

  def addMember(employee: Employee): Unit = {
      val newEmployees = new Array[Employee](employees.length+1)
      Array.copy(employees, 0, newEmployees, 0, employees.length)
      newEmployees(employees.length)=employee
      employees = newEmployees
  }

  def addSubMembers(subDept: Department): Unit = {
      val newSubDepartments = new Array[Department](subDepartments.length+1)
      Array.copy(subDepartments, 0, newSubDepartments,0, subDepartments.length)
      newSubDepartments(subDepartments.length)=subDept
      subDepartments = newSubDepartments
  }

  override def printOrganizationStructure(prefix: String = ""): Unit = {
    super.printOrganizationStructure(prefix)
    employees.foreach(emp => emp.printOrganizationStructure(prefix + "  ├── "))
    subDepartments.foreach(_.printOrganizationStructure(prefix + "  "))
  }
}

def findDepartment(dept: Department, path: Array[String]): Option[Department] = {
  if (path.isEmpty) Some(dept)
  else {
    dept.subDepartments.find(_.name == path.head) match {
      case Some(subDept) => findDepartment(subDept, path.tail)
      case None => None
    }
  }
}

object OrgApp extends App {
  val rootParent = new Department("Organization")
  
  val finance = new Department("Finance")
  val payments = new Department("Payments")
  val sales = new Department("Sales")
  val marketing = new Department("Marketing")
  val advertisements = new Department("Advertisements")
  val salesManagement = new Department("SalesManagement")

  rootParent.addSubMembers(finance)
  finance.addSubMembers(payments)
  finance.addSubMembers(sales)
  sales.addSubMembers(marketing)
  sales.addSubMembers(advertisements)
  sales.addSubMembers(salesManagement)
  
  def getEmployeeDetails(): Employee = {
    val sno = readLine("Enter sno: ").toInt
    val name = readLine("Enter name: ")
    val city = readLine("Enter city: ")
    Employee(sno, name, city)
  }

  def getDepartmentDetails(): Array[String] = {
    readLine("Enter department path (separated by '/'): ").split("/")
  }

  var flag = true
  while (flag) {
    readLine("Enter a command (add/print/exit): ").toLowerCase match {
      case "add" =>
        val employee = getEmployeeDetails()
        val path = getDepartmentDetails()
        findDepartment(rootParent, path) match {
          case Some(dept) => dept.addMember(employee)
          case None => println("Department not found!")
        }

      case "print" =>
        rootParent.printOrganizationStructure()

      case "exit" =>
        flag = false

      case _ =>
        println("Unknown command. Please enter 'add', 'print', or 'exit'.")
    }
  }
}
