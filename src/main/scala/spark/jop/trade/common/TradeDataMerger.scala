package spark.jop.trade.common

import org.apache.spark.sql.{Dataset, SparkSession}
import spark.jop.trade.model.TradeData
import spark.util.ImplicitsUtil.DatasetOps

object TradeDataMerger {
  def findChange(oldDs: Dataset[TradeData], newDs: Dataset[TradeData])(
    implicit spark: SparkSession): Dataset[TradeData] = {
    import spark.implicits._
    val colNames = Seq("tradeId")

    val joinCondition = colNames.map(colName => oldDs(colName) === newDs(colName)).reduce(_ && _)

    oldDs.joinWith(newDs, joinCondition, "full").collectMatch {
      case (null, newRecord)                                => newRecord
      case (oldRecord, null)                                => oldRecord
      case (oldRecord, newRecord) if newRecord != oldRecord => newRecord
    }
  }

}
