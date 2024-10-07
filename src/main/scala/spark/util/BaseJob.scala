package spark.util

import org.apache.spark.sql.SparkSession

trait BaseJob {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName(appName)
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    run(spark)
  }
  def appName: String

  def run(implicit spark: SparkSession)
}
