package flink.jop.getTrade.model

import play.api.libs.json.{Format, Json}

case class TradeData(symbol: String,
                     tradeId: Long,
                     price: String,
                     quantity: String,
                     tradeTime: Long
 )

object TradeData {
  implicit val tradeDataFormat: Format[TradeData] = Json.format[TradeData]
}