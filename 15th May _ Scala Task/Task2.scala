/*Create a function 
which of the format

getAlgorithm(type: String): (Array[int])=>Unit

implement

bubblesort
insertionsort
quicksort
mergesort
radixsort 
binary search

logics in six different functions and return those
function based on the user input

and the user needs to execute the logic that is returned */

object SortingSearchingAlgorithms {

  // Bubble Sort
  def bubbleSort(arr: Array[Int]): Unit = {
    for (i <- arr.indices) {
      for (j <- 0 until arr.length - i - 1) {
        if (arr(j) > arr(j + 1)) {
          val temp = arr(j)
          arr(j) = arr(j + 1)
          arr(j + 1) = temp
        }
      }
    }
  }

  // Insertion Sort
  def insertionSort(arr: Array[Int]): Unit = {
    for (i <- 1 until arr.length) {
      val key = arr(i)
      var j = i - 1
      while (j >= 0 && arr(j) > key) {
        arr(j + 1) = arr(j)
        j -= 1
      }
      arr(j + 1) = key
    }
  }

  // Quick Sort
  def quickSort(arr: Array[Int]): Unit = {
    def quickSortHelper(arr: Array[Int], low: Int, high: Int): Unit = {
      if (low < high) {
        val pi = partition(arr, low, high)
        quickSortHelper(arr, low, pi - 1)
        quickSortHelper(arr, pi + 1, high)
      }
    }

    def partition(arr: Array[Int], low: Int, high: Int): Int = {
      val pivot = arr(high)
      var i = low - 1
      for (j <- low until high) {
        if (arr(j) <= pivot) {
          i += 1
          val temp = arr(i)
          arr(i) = arr(j)
          arr(j) = temp
        }
      }
      val temp = arr(i + 1)
      arr(i + 1) = arr(high)
      arr(high) = temp
      i + 1
    }

    quickSortHelper(arr, 0, arr.length - 1)
  }

  // Merge Sort
  def mergeSort(arr: Array[Int]): Unit = {
    def mergeSortHelper(arr: Array[Int], l: Int, r: Int): Unit = {
      if (l < r) {
        val m = (l + r) / 2
        mergeSortHelper(arr, l, m)
        mergeSortHelper(arr, m + 1, r)
        merge(arr, l, m, r)
      }
    }

    def merge(arr: Array[Int], l: Int, m: Int, r: Int): Unit = {
      val n1 = m - l + 1
      val n2 = r - m
      val L = new Array[Int](n1)
      val R = new Array[Int](n2)
      for (i <- 0 until n1) L(i) = arr(l + i)
      for (j <- 0 until n2) R(j) = arr(m + 1 + j)
      var i = 0
      var j = 0
      var k = l
      while (i < n1 && j < n2) {
        if (L(i) <= R(j)) {
          arr(k) = L(i)
          i += 1
        } else {
          arr(k) = R(j)
          j += 1
        }
        k += 1
      }
      while (i < n1) {
        arr(k) = L(i)
        i += 1
        k += 1
      }
      while (j < n2) {
        arr(k) = R(j)
        j += 1
        k += 1
      }
    }

    mergeSortHelper(arr, 0, arr.length - 1)
  }

  // Radix Sort
  def radixSort(arr: Array[Int]): Unit = {
    def countingSort(arr: Array[Int], exp: Int): Unit = {
      val n = arr.length
      val output = new Array[Int](n)
      val count = Array.fill(10)(0)
      for (i <- arr.indices) count((arr(i) / exp) % 10) += 1
      for (i <- 1 until 10) count(i) += count(i - 1)
      for (i <- n - 1 to 0 by -1) {
        output(count((arr(i) / exp) % 10) - 1) = arr(i)
        count((arr(i) / exp) % 10) -= 1
      }
      for (i <- arr.indices) arr(i) = output(i)
    }

    def getMax(arr: Array[Int]): Int = arr.max

    val m = getMax(arr)
    var exp = 1
    while (m / exp > 0) {
      countingSort(arr, exp)
      exp *= 10
    }
  }

  // Binary Search
  def binarySearch(arr: Array[Int], target: Int): Int = {
    var left = 0
    var right = arr.length - 1
    while (left <= right) {
      val mid = left + (right - left) / 2
      if (arr(mid) == target) return mid
      else if (arr(mid) < target) left = mid + 1
      else right = mid - 1
    }
    -1
  }

  def getAlgorithm(algorithmType: String): (Array[Int]) => Unit = algorithmType match {
    case "bubblesort"    => bubbleSort
    case "insertionsort" => insertionSort
    case "quicksort"     => quickSort
    case "mergesort"     => mergeSort
    case "radixsort"     => radixSort
    case "binarysearch"  => _ => println("Binary search requires a target value. Use the 'binarySearch' method directly.")
    case _ => throw new IllegalArgumentException("Unsupported algorithm type")
  }

  // Example usage
  def main(args: Array[String]): Unit = {
    // Demonstrate Bubble Sort
    var arr = Array(64, 34, 25, 12, 22, 11, 90)
    println(s"Original array: ${arr.mkString(", ")}")
    var sortingAlgorithm = getAlgorithm("bubblesort")
    sortingAlgorithm(arr)
    println(s"Sorted array using Bubble Sort: ${arr.mkString(", ")}")

    // Demonstrate Insertion Sort
    arr = Array(64, 34, 25, 12, 22, 11, 90)
    println(s"\nOriginal array: ${arr.mkString(", ")}")
    sortingAlgorithm = getAlgorithm("insertionsort")
    sortingAlgorithm(arr)
    println(s"Sorted array using Insertion Sort: ${arr.mkString(", ")}")

    // Demonstrate Quick Sort
    arr = Array(64, 34, 25, 12, 22, 11, 90)
    println(s"\nOriginal array: ${arr.mkString(", ")}")
    sortingAlgorithm = getAlgorithm("quicksort")
    sortingAlgorithm(arr)
    println(s"Sorted array using Quick Sort: ${arr.mkString(", ")}")

    // Demonstrate Merge Sort
    arr = Array(64, 34, 25, 12, 22, 11, 90)
    println(s"\nOriginal array: ${arr.mkString(", ")}")
    sortingAlgorithm = getAlgorithm("mergesort")
    sortingAlgorithm(arr)
    println(s"Sorted array using Merge Sort: ${arr.mkString(", ")}")

    // Demonstrate Radix Sort
    arr = Array(170, 45, 75, 90, 802, 24, 2, 66)
    println(s"\nOriginal array: ${arr.mkString(", ")}")
    sortingAlgorithm = getAlgorithm("radixsort")
    sortingAlgorithm(arr)
    println(s"Sorted array using Radix Sort: ${arr.mkString(", ")}")

    // Demonstrate Binary Search
    val searchArr = Array(11, 12, 22, 25, 34, 64, 90)
    val target = 22
    val index = binarySearch(searchArr, target)
    println(s"\nIndex of $target using Binary Search: $index")
  }
}
