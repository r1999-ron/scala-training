/*
RDDs (Resilient Distributed Datasets)
RDDs are the fundamental data structure of Spark, representing an immutable, distributed collection of objects that can be processed in parallel.

Operations on RDDs
1.Transformation: These are lazy operations that define a new RDD based on the current RDD.

   map
   filter
   flatMap
2.Action: These are operations that trigger the execution of transformations to return a value to the driver program or write data to an external storage system.

   collect
   reduce
   count

*/

import org.apache.spark.{SparkConf, SparkContext}

object WordCountRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCountRDD").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // Read the text file into an RDD
    val input = sc.textFile("/Users/ronak/Downloads/textfile.txt")

    // Transformations
    val words = input.flatMap(line => line.split(" ")) // Split lines into words
    val wordCounts = words.map(word => (word, 1)).reduceByKey(_ + _) // Count occurrences of each word

    // Action
    wordCounts.collect().foreach(println) // Collect and print results

    sc.stop()
  }
}
/* 

Comments: This example demonstrates the common RDD transformations (flatMap, map, reduceByKey) and the collect action.


 */