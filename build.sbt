lazy val root = (project in file(".")).
  settings(
    name := "spark-streaming-websocket",
    organization := "com.github.atemerev",
    scalaVersion := "2.11.8",
    version := "0.1.1-SNAPSHOT",
    sbtVersion := "0.13.12",
    libraryDependencies ++= Seq(
      "org.jfarcand" % "wcs" % "1.5",
      "org.apache.spark" %% "spark-core" % "2.0.0" % "provided",
      "org.apache.spark" %% "spark-streaming" % "2.0.0" % "provided",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test"
    )
  )
