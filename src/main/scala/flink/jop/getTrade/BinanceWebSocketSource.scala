import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class BinanceWebSocketSource(symbol: String) extends SourceFunction[String] {
  @volatile private var isRunning = true
  @volatile private var client: WebSocketClient = _

  override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    val endpoint = s"wss://stream.binance.com:9443/ws/${symbol.toLowerCase}@trade"

    client = new WebSocketClient(new URI(endpoint)) {
      override def onOpen(handshakedata: ServerHandshake): Unit = {
        println("WebSocket bağlantısı açıldı.")
      }

      override def onMessage(message: String): Unit = {
        ctx.collect(message)
      }

      override def onClose(code: Int, reason: String, remote: Boolean): Unit = {
        println(s"WebSocket bağlantısı kapandı: $reason")
        isRunning = false
      }

      override def onError(ex: Exception): Unit = {
        println(s"WebSocket hatası: ${ex.getMessage}")
      }
    }

    client.connect()

    // Bağlantı kurulana kadar bekle
    while (!client.getConnection.isOpen && isRunning) {
      Thread.sleep(100)
    }

    // Bağlantı açıkken çalışmaya devam et
    while (isRunning && client.getConnection.isOpen) {
      Thread.sleep(100)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
    if (client != null && !client.isClosed) {
      client.close()
    }
  }
}