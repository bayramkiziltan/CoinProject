package spark.jop.trade.model

import org.apache.spark.sql.types._

object Constants {

  val tradeDataSchema = new StructType()
    .add("symbol", StringType)
    .add("tradeId", LongType)
    .add("price", StringType)
    .add("quantity", StringType)
    .add("tradeTime", LongType)

}
