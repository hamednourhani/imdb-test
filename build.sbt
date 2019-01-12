name := "imdb-test"

organization in ThisBuild := "ir.itstar"
version in ThisBuild := "1.0.0"
scalaVersion in ThisBuild := "2.12.8"

lazy val settings = Seq(
  scalacOptions ++= Seq(
    "-unchecked",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-encoding",
    "utf8",
    "-Ypartial-unification"
  ),
  resolvers ++= Seq(
    "Local Maven Repository".at("file://" + Path.userHome.absolutePath + "/.m2/repository"),
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  )
)

lazy val dependencies =
  new {
    val akkaV         = "2.5.6"
    val akkaHttpV     = "10.1.5"
    val slickVersion  = "3.2.3"
    val akkaVersion   = "2.5.18"
    val catsV         = "1.5.0"
    val scalaLoggingV = "3.7.2"
    val postgresV     = "42.1.4"
    val hikariCpV     = "2.7.0"
    val slickPgV      = "0.16.3"
    val alpakaV       = "1.0-M1"

    val akkaHttp         = "com.typesafe.akka"          %% "akka-http"                 % akkaHttpV
    val akkaStream       = "com.typesafe.akka"          %% "akka-stream"               % akkaVersion
    val alpakaSlick      = "com.lightbend.akka"         %% "akka-stream-alpakka-slick" % alpakaV
    val alpakaCSV        = "com.lightbend.akka"         %% "akka-stream-alpakka-csv"   % alpakaV
    val logging          = "com.typesafe.scala-logging" %% "scala-logging"             % scalaLoggingV
    val slick            = "com.typesafe.slick"         %% "slick"                     % slickVersion
    val postgres         = "org.postgresql"             % "postgresql"                 % postgresV
    val hikariCP         = "com.zaxxer"                 % "HikariCP"                   % hikariCpV
    val spray            = "com.typesafe.akka"          %% "akka-http-spray-json"      % akkaHttpV
    val catsCore         = "org.typelevel"              %% "cats-core"                 % catsV
    val catsMacros       = "org.typelevel"              %% "cats-macros"               % catsV
    val catsKernel       = "org.typelevel"              %% "cats-kernel"               % catsV
    val slickPgSprayJson = "com.github.tminglei"        %% "slick-pg_spray-json"       % slickPgV
    val slickPg          = "com.github.tminglei"        %% "slick-pg"                  % slickPgV
  }

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

lazy val commonDependencies = Seq(
  dependencies.slick,
  dependencies.slickPg,
  dependencies.slickPgSprayJson,
  dependencies.postgres,
  dependencies.hikariCP,
  dependencies.logging,
)

lazy val global = project
  .in(file("."))
  .aggregate(common, dummy, rest)

lazy val common =
  project
    .settings(
      settings,
      libraryDependencies ++= commonDependencies
    )

lazy val dummy =
  project
    .settings(
      settings,
      assemblySettings,
      libraryDependencies ++= commonDependencies ++ Seq(
        dependencies.akkaStream,
        dependencies.alpakaSlick,
        dependencies.alpakaCSV
      )
    )
    .dependsOn(common)

lazy val rest =
  project
    .settings(
      settings,
      assemblySettings,
      libraryDependencies ++= commonDependencies ++ Seq(
        dependencies.akkaStream,
        dependencies.akkaHttp,
        dependencies.spray,
        dependencies.catsCore,
        dependencies.catsKernel,
        dependencies.catsMacros
      )
    )
    .dependsOn(common)

addCommandAlias(
  "fmt",
  ";scalafmtSbt;scalafmt;test:scalafmt"
)

addCommandAlias(
  "wip",
  ";clean;compile;fmt;test"
)

addCommandAlias(
  "dummy",
  "; project dummy; run"
)

addCommandAlias(
  "rest",
  "; project rest; run"
)
