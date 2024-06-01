/*
Create a function that takes callback as a parameter

Application:

* the function should pass a 10x10 matrix  with seat numbers

* filled seats must be represented with X

* available seats must be represented with seat numbers

* the user callback must print the matrix

* should return an array of seat numbers to the function

* the function would allocate the seats and callback
with modified matrix

* new seat booking can continue again

* if the user instead of sending array send null
the process should stop
*/

import scala.io.StdIn.readLine

object SeatAllocator {
  def generateMatrix(callback: Array[Array[String]] => Unit): Array[String] = {
    val matrix = Array.ofDim[String](10, 10)
    for (i <- 0 until 10; j <- 0 until 10) {
      matrix(i)(j) = (i * 10 + j + 1).toString
    }
    callback(matrix)
    
    matrix.flatten
  }
  def printMatrix(matrix: Array[Array[String]]): Unit = {
    for (row <- matrix) {
      println(row.mkString(" "))
    }
  }
  def allocateSeats(matrix: Array[Array[String]], seats: Array[String]): Array[Array[String]] = {
    println("Enter the seat numbers to book (comma separated), or type 'null' to stop:")
    val input = readLine().trim

    if (input.equalsIgnoreCase("null")) {
      return null
    }

    val seatNumbers = input.split(",").map(_.trim).toSet

    for (i <- 0 until 10; j <- 0 until 10) {
      if (seatNumbers.contains(matrix(i)(j))) {
        matrix(i)(j) = "X" // Mark seat as booked with X
      }
    }

    matrix
  }

  def main(args: Array[String]): Unit = {
    var seats = generateMatrix(printMatrix)

    while (true) {
      
      val matrix = Array.ofDim[String](10, 10)
      for (i <- 0 until 10; j <- 0 until 10) {
        matrix(i)(j) = seats(i * 10 + j)
      }

      val modifiedMatrix = allocateSeats(matrix, seats)
      if (modifiedMatrix == null) {
        println("Booking process stopped.")
        return
      }

      printMatrix(modifiedMatrix)
      
      seats = modifiedMatrix.flatten
    }
  }
}