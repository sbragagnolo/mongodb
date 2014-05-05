name := "mongodb"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "net.vz.mongodb.jackson" %% "play-mongo-jackson-mapper" % "1.1.0"
)     

play.Project.playScalaSettings
