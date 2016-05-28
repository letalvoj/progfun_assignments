package cz.letal.progfun.toying

import java.util.concurrent.atomic.AtomicInteger

object RecursiveStreamComplexity extends App {

  val integer = new AtomicInteger()

  val foo: Stream[Int] = 1 #:: foo.map(i => {
    integer.incrementAndGet()

    i + 2
  })

  println(s"This ${foo.take(10).mkString(", ")} took ${integer.get()} operations")

}