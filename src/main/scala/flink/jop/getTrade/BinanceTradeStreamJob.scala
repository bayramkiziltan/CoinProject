import org.apache.flink.streaming.api.scala._
import play.api.libs.json._

object BinanceTradeStreamJob {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // İşlemek istediğiniz sembolü girin (örneğin "btcusdt")
    val symbol = "btcusdt"

    // BinanceWebSocketSource'u kullanarak veri akışını oluşturun
    val tradeStream = env.addSource(new BinanceWebSocketSource(symbol))

    tradeStream
      .map(tradeJson => processTradeData(tradeJson))
      .print()

    env.execute("Binance Trade Stream Processing")
  }

  def processTradeData(tradeJson: String): String = {
    // JSON verisini parse edin
    val json = Json.parse(tradeJson)

    // İlgili alanları alın
    val eventType = (json \ "e").asOpt[String].getOrElse("unknown")
    val eventTime = (json \ "E").asOpt[Long].getOrElse(0L)
    val symbol = (json \ "s").asOpt[String].getOrElse("unknown")
    val tradeId = (json \ "t").asOpt[Long].getOrElse(0L)
    val price = (json \ "p").asOpt[String].getOrElse("0")
    val quantity = (json \ "q").asOpt[String].getOrElse("0")
    val buyerOrderId = (json \ "b").asOpt[Long].getOrElse(0L)
    val sellerOrderId = (json \ "a").asOpt[Long].getOrElse(0L)
    val tradeTime = (json \ "T").asOpt[Long].getOrElse(0L)
    val isBuyerMaker = (json \ "m").asOpt[Boolean].getOrElse(false)

    // İstediğiniz formatta çıktıyı oluşturun
    s"Sembol: $symbol, Fiyat: $price, Miktar: $quantity, Trade Id: $tradeId, Zaman: $tradeTime"
  }
}