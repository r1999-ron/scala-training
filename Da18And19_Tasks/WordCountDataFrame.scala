/* 
DataFrames
DataFrames are a higher-level abstraction of RDDs, designed to make large-scale data processing easier and more efficient, with an optimized execution plan (Catalyst optimizer).

Operations on DataFrames
1.Transformation:
   select
   filter
   groupBy
2.Action:
    show
    collect
    count
 */

import org.apache.spark.sql.{SparkSession, functions => F}

object WordCountDataFrame {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("WordCountDataFrame").master("local[*]").getOrCreate()

    // Read the text file into a DataFrame
    val input = spark.read.text("/Users/ronak/Downloads/textfile.txt")

    // Transformations
    val words = input.select(F.explode(F.split(F.col("value"), " ")).as("word")) // Split lines into words
    val wordCounts = words.groupBy("word").count() // Count occurrences of each word

    // Action
    wordCounts.show() // Show results

    spark.stop()
  }
}
/* 
Comments: This example uses DataFrame transformations (select, explode, split, groupBy) and the show action to perform a word count.


 */