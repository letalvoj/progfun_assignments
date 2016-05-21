import sbt.Keys._

name := "progfun_assignments"
version := "1.0"
scalaVersion := "2.11.7"

val sharedDependencies = Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
  "org.scala-lang" % "scala-parser-combinators" % "2.11.0-M4",
  "junit" % "junit" % "4.10" % "test"
)

val configure = (project: Project) => project.settings(
  libraryDependencies ++= sharedDependencies,
  scalaVersion := "2.11.7"
)

lazy val week1 = configure(project.in(file("week1")))
lazy val week2 = configure(project.in(file("week2")))
lazy val week3 = configure(project.in(file("week3")))

lazy val root = project.in(file(".")).aggregate(week1, week2, week3)



