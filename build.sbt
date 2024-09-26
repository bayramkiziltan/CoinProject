import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"
val flinkVersion   = "1.14.6"
val logbackVersion = "1.2.10"
val sparkVersion   = "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "CoinProject"
  )

libraryDependencies ++= Seq(
  // scala
  "org.scala-lang" % "scala-reflect" % "2.12.18",
  "org.scala-lang" % "scala-library" % "2.12.18",
  // spark-sql
  "org.apache.spark" %% "spark-sql-api" % sparkVersion,
  "org.apache.spark" %% "spark-hive"    % sparkVersion,
  // streaming
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  // streaming-kafka
  "org.apache.spark" % "spark-sql-kafka-0-10_2.12" % sparkVersion,
  // low-level integrations
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  // postgres
  "org.postgresql"        % "postgresql"  % "42.7.3",
  "com.github.pureconfig" %% "pureconfig" % "0.17.6",
  // Flink
  "org.apache.flink" %% "flink-clients"         % flinkVersion,
  "org.apache.flink" %% "flink-scala"           % flinkVersion,
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion,
  // Flink connectors
  "org.apache.flink" %% "flink-connector-kafka"     % flinkVersion,
  "org.apache.flink" %% "flink-connector-cassandra" % flinkVersion,
  "org.apache.flink" %% "flink-connector-jdbc"      % flinkVersion,
  // Flink logging
  "ch.qos.logback"    % "logback-core"    % logbackVersion,
  "ch.qos.logback"    % "logback-classic" % logbackVersion,
  "com.typesafe.play" % "play-json_2.12"  % "2.10.6",
  //Socket
  "org.java-websocket" % "Java-WebSocket" % "1.5.2",
)
fork := true
//assemblyJarName in assembly := "playground-job.jar"
//assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x                             => MergeStrategy.first
//}
