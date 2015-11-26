name := """Facebook-API-Simulation"""

version := "0.0.1"

scalaVersion := "2.11.6"

lazy val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)

fork in run := true
