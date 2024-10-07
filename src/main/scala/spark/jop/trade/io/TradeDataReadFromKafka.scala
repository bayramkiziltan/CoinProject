package spark.jop.trade.io

import org.apache.spark.sql.{Dataset, SparkSession}
import spark.jop.trade.model.TradeData

object TradeDataReadFromKafka {
  def readStreamProductKafka(topic: String)(implicit spark: SparkSession) = {
    import spark.implicits._
    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "kafka.kafka-connect.orb.local:9092")
      .option("subscribe", s"$topic") //topic name
      .option("startingOffsets", "earliest")
      .load()
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)") // serialize-deserialize
  }
}
