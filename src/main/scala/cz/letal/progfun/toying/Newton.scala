package cz.letal.progfun.toying

import scala.annotation.tailrec

object Newton extends App {

  def sqrt(value: Double, precision: Double = 1E-6): Double = {

    def isPreciseEnough(x: Double): Boolean = Math.abs((x * x - value) / value) < precision

    def newtonStep(x: Double): Double = {
      val functionValue = x * x - value
      val gradient = 2 * x

      x - functionValue / gradient
    }

    @tailrec
    def sqrtIter(xPrev: Double): Double = {
      val xNext = newtonStep(xPrev)

      if (isPreciseEnough(xNext))
        xNext
      else
        sqrtIter(xNext)
    }

    sqrtIter(value)
  }

  println(s"${Math.sqrt(2)} - Math.sqrt")
  println("----------------------------------")
  for (a <- 1 to 15) {
    println(s"${sqrt(2, Math.pow(10, -a))} - precision $a significant digits")
  }
}
