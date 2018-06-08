name := "scanamo-with-sbt"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1",
  "com.gu" %% "scanamo-alpakka" % "1.0.0-M6"
)
