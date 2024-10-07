package flink.job.getTrade.jobs

import flink.jop.getTrade.io.{BinanceWebSocketSource, KafkaSink}
import flink.jop.getTrade.model.{RawTradeData, TradeData}
import flink.jop.getTrade.common.TradeDataFormat._
import org.apache.flink.streaming.api.scala._
import play.api.libs.json._

import scala.util.Try

object BinanceTradeStreamJob {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // İşlemek istediğiniz sembolü girin (örneğin "btcusdt")
    val symbol = "btcusdt"

    // WebSocket üzerinden işlem verilerini alın
    val tradeStream = env.addSource(new BinanceWebSocketSource(symbol))

    // Verileri işleyin
    val processedStream = tradeStream
      .flatMap(tradeJson => processTradeData(tradeJson))
      .filter { tradeData =>
        // Fiyat ve miktarın pozitif olup olmadığını kontrol edin
        Try(tradeData.p.toDouble).toOption.exists(_ > 0) &&
          Try(tradeData.q.toDouble).toOption.exists(_ > 0)
      }
      .map { tradeData =>
        // RawTradeData'yı TradeData'ya dönüştürün
        TradeData(
          symbol = tradeData.s,
          tradeId = tradeData.t,
          price = tradeData.p,
          quantity = tradeData.q,
          tradeTime = tradeData.T
        )
      }
      .map(tradeData => Json.toJson(tradeData).toString())

    KafkaSink.writeToKafka(processedStream, "localhost:9092", "trade_topic")
    env.execute("Binance Trade Stream Processing")
  }
  def processTradeData(tradeJson: String): Option[RawTradeData] = {
    // JSON verisini parse edin ve TradeData'ya dönüştürün
    Json.parse(tradeJson).validate[RawTradeData] match {
      case JsSuccess(tradeData, _) => Some(tradeData)
      case JsError(errors) =>
        println(s"JSON parse hatası: $errors")
        None

    }

  }
}