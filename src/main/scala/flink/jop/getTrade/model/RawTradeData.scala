package flink.jop.getTrade.model

import play.api.libs.json._
import play.api.libs.functional.syntax._

// TradeData modelini tanımlayın
case class RawTradeData(
                      s: String,   // Symbol
                      t: Long,     // Trade ID
                      p: String,   // Price
                      q: String,   // Quantity
                      T: Long      // Trade time
                    )

