package spark.jop.trade.model

case class TradeData(symbol: String,
                     tradeId: Long,
                     price: String,
                     quantity: String,
                     tradeTime: Long)
