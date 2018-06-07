name := "scanamo-with-sbt"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.gu" %% "scanamo" % "1.0.0-M6",
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  //"com.lightbend.akka" %% "akka-stream-alpakka-dynamodb" % "0.19",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "org.scalatest" %% "scalatest" % "3.0.1"
)
