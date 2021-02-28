val http4sVersion = "1.0.0-M10"
val scala3Version = "3.0.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "canon-calc",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      ("dev.zio" %% "zio" % "1.0.4-2").withDottyCompat(scalaVersion.value),
      ("org.http4s" %% "http4s-dsl" % http4sVersion).withDottyCompat(scalaVersion.value),
      ("org.http4s" %% "http4s-blaze-server" % http4sVersion).withDottyCompat(scalaVersion.value),
      ("org.http4s" %% "http4s-blaze-client" % http4sVersion).withDottyCompat(scalaVersion.value),
      ("dev.zio" %% "zio-interop-cats" % "2.2.0.1").withDottyCompat(scalaVersion.value),
      ("org.http4s" %% "http4s-circe" % http4sVersion).withDottyCompat(scalaVersion.value)
//      ("io.circe" %%% "circe-core" % "0.13.0").withDottyCompat(scalaVersion.value),
//      ("io.circe" %%% "circe-parser" % "0.13.0").withDottyCompat(scalaVersion.value)
    )
  )
