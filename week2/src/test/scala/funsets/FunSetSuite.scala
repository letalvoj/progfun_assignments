package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import scala.collection.immutable

@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    * val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {
      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection") {
    new TestSets {
      val l1 = (a: Int) => a % 2 == 0
      val l2 = (a: Int) => immutable.Set(1, 2, 3, 4, 5) contains a
      val s = intersect(l1, l2)

      FunSets.printSet(s)
    }
  }

  test("forall") {
    new TestSets {
      val yes = (a: Int) => immutable.Set(2, 4, 6, 8, 10) contains a
      val no = (a: Int) => immutable.Set(3, 4, 5) contains a
      val even = (a: Int) => a % 2 == 0

      assert(FunSets.forall(yes, even), "All even")
      assert(!FunSets.forall(no, even), "Some odd")
    }
  }

  test("exists") {
    new TestSets {
      val yes = (a: Int) => immutable.Set(1, 2) contains a
      val no = (a: Int) => immutable.Set(1, 3, 5, 7) contains a
      val even = (a: Int) => a % 2 == 0

      assert(FunSets.exists(yes, even), "One even")
      assert(!FunSets.exists(no, even), "All odd")
    }
  }

  test("map") {
    new TestSets {
      val original = (a: Int) => immutable.Set(1, 6, 10) contains a
      val shifted = FunSets.map(original, a => a - 1)

      assert(shifted(0), "0")
      assert(shifted(5), "5")
      assert(shifted(9), "9")

      assert(!shifted(1), "1")
      assert(!shifted(6), "6")
      assert(!shifted(10), "10")
    }
  }

}
