import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "CoinProject"
  )

libraryDependencies ++= Seq(
  // scala
  "org.scala-lang" % "scala-reflect" % "2.12.18",
  "org.scala-lang" % "scala-library" % "2.12.18",
  // spark-sql
  "org.apache.spark" %% "spark-sql"  % "3.5.0",
  "org.apache.spark" %% "spark-hive" % "3.5.0",
  // streaming
  "org.apache.spark" %% "spark-streaming" % "3.5.0",
  // streaming-kafka
  "org.apache.spark" % "spark-sql-kafka-0-10_2.12" % "3.5.0",
  // low-level integrations
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % "3.5.0",
  // postgres
  "org.postgresql"        % "postgresql"  % "42.7.3",
  "com.github.pureconfig" %% "pureconfig" % "0.17.6"
)

//assemblyJarName in assembly := "playground-job.jar"
//assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x                             => MergeStrategy.first
//}
