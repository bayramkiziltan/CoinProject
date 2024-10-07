package flink.jop.getTrade.common

import flink.jop.getTrade.model.RawTradeData
import play.api.libs.json._
import play.api.libs.functional.syntax._

object TradeDataFormat {
  // Implicit Reads
  implicit val tradeDataReads: Reads[RawTradeData] = (
    (JsPath \ "s").read[String] and
      (JsPath \ "t").read[Long] and
      (JsPath \ "p").read[String] and
      (JsPath \ "q").read[String] and
      (JsPath \ "T").read[Long]
    )(RawTradeData.apply _)

  // Implicit Writes
  implicit val tradeDataWrites: Writes[RawTradeData] = Json.writes[RawTradeData]
}
//
