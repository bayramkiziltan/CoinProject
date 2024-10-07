package spark.jop.trade

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object TradeDataCountJob {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder
      .master("local") // Gerekirse uygun master URL'sini belirtin
      .appName("TradeDataCountJob")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    // Data lake yolunu belirtin (verilerin yazıldığı yer)
    val dataLakePath = "src/main/resources/datalake/tradeDataStg"

    // Data lake'den verileri okuyun
    val tradeDataDF = spark.read
      .format("parquet")
      .load(dataLakePath)

    // Kayıt sayısını hesaplayın
    val recordCount = tradeDataDF.count()

    // Kayıt sayısını ekrana yazdırın
    println(s"Data lake'e yazılan toplam kayıt sayısı: $recordCount")

    // SparkSession'ı sonlandırın
    spark.stop()
  }
}
