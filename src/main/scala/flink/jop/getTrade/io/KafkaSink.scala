package flink.jop.getTrade.io


import flink.jop.getTrade.model.RawTradeData
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import play.api.libs.json.Json

import java.util.Properties

object KafkaSink {
  def createKafkaProducer(brokers: String, topic: String): FlinkKafkaProducer[String] = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", brokers)
    // Gerekli diğer Kafka ayarlarını ekleyin

    new FlinkKafkaProducer[String](
      topic,
      new SimpleStringSchema(),
      properties
    )
  }

  // İşlenmiş akışı alıp Kafka'ya yazan fonksiyon
  def writeToKafka(stream: DataStream[String], brokers: String, topic: String): Unit = {
    val kafkaProducer = createKafkaProducer(brokers, topic)
    stream
      .addSink(kafkaProducer)
  }
}
