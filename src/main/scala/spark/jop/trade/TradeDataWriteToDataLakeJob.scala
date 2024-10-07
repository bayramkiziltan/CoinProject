package spark.jop.trade

import org.apache.spark.sql.functions.from_json
import org.apache.spark.sql.{Dataset, SparkSession}
import spark.jop.trade.common.TradeDataMerger.findChange
import spark.jop.trade.io.TradeDataReadFromKafka.readStreamProductKafka
import spark.jop.trade.io.TradeDataReadFromDataLake.readDataLake
import spark.jop.trade.model.Constants.tradeDataSchema
import spark.jop.trade.model.TradeData
import spark.util.BaseJob


object TradeDataWriteToDataLakeJob extends BaseJob {

  override def appName: String = "Trade Data CDC Job"

  override def run(implicit spark: SparkSession): Unit = {
    import spark.implicits._
    val tradeDataDF = readStreamProductKafka("trade_topic").selectExpr("value AS jsonString")

    val tradeDataDS = tradeDataDF.select(from_json($"jsonString", tradeDataSchema).as("data"))
      .select("data.*").as[TradeData]

    val dataLakePath = "src/main/resources/datalake/tradeDataStg"

    val oldDs = readDataLake(dataLakePath)

    val changes = findChange(oldDs, tradeDataDS)

    val query = changes.writeStream
      .outputMode("append")
      .format("parquet")
      .option("path", "src/main/resources/datalake/tradeDataStg")
      .option("checkpointLocation", "src/main/resources/datalake/tradeDataStg/_checkpoints")
      .start()

    query.awaitTermination()

  }
}
