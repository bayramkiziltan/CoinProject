package spark.jop.trade.io

import org.apache.spark.sql.{Dataset, SparkSession}
import spark.jop.trade.model.TradeData

object TradeDataReadFromDataLake {
  def readDataLake(dataLakePath: String)(
    implicit spark: SparkSession): Dataset[TradeData] = {
    import spark.implicits._
    // Veri gölünden veriyi okuma
     spark.readStream
      .format("parquet")
      .load(dataLakePath)
       .as[TradeData]
    }

}
