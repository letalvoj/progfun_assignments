import sbt.Keys._

name := "progfun_assignments"
version := "1.0"
scalaVersion := "2.11.7"

lazy val root = project.in(file(".")).aggregate(week1)

lazy val week1 = project.in(file("week1")).settings(
  libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.11" % "2.2.6",
    "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
    "junit" % "junit" % "4.10" % "test"
  )
).settings(
  scalaVersion := "2.11.7"
)


