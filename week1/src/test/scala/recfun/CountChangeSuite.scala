package recfun

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, ShouldMatchers}

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite with ShouldMatchers {

  import Week1.countChange

  test("countChange: example given in instructions") {
    countChange(4, List(1, 2)) should be(3)
  }

  test("countChange: sorted CHF") {
    countChange(300, List(5, 10, 20, 50, 100, 200, 500)) should be(1022)
  }

  test("countChange: no pennies") {
    countChange(301, List(5, 10, 20, 50, 100, 200, 500)) should be(0)
  }

  test("countChange: unsorted CHF") {
    countChange(300, List(500, 5, 50, 100, 20, 200, 10)) should be(1022)
  }
}
