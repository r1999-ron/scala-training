import org.apache.spark.sql.{SparkSession, Dataset, Encoders}

/* 

Datasets combine the benefits of RDDs (strongly-typed, functional programming capabilities) with the optimizations of DataFrames. They provide type-safe, object-oriented programming.

Operations on Datasets
1Transformation:
   map
   filter
   groupBy

2.Action:
   show
   collect
   count
 */

object WordCountDataset {
  case class Line(value: String)
  case class WordCount(word: String, count: Long)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("WordCountDataset").master("local[*]").getOrCreate()

    import spark.implicits._

    // Read the text file into a Dataset
    val input: Dataset[Line] = spark.read.textFile("/Users/ronak/Downloads/textfile.txt").map(Line(_))

    // Transformations
    val words = input.flatMap(line => line.value.split(" ")).withColumnRenamed("value", "word")
    val wordCounts = words.groupBy("word").count().as[WordCount] // Count occurrences of each word

    // Action
    wordCounts.show() // Show results

    spark.stop()
  }
}

/* 
Comments: This example uses Dataset transformations (flatMap, groupBy, count) and the show action, providing type safety and leveraging Spark's optimizations.
 */