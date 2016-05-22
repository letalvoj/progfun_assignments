package patmat

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, ShouldMatchers}
import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite with ShouldMatchers {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      weight(t1) should be(5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      chars(t2) should be(List('a', 'b', 'd'))
    }
  }


  test("string2chars(\"hello, world\")") {
    string2Chars("hello, world") should be(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) should be(List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    combine(leaflist) should be(List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }


  test("decode and encode a very short text should be identity") {
    val fc = Huffman.frenchCode
    decode(fc, encode(fc)("letsmakeitmorecomplicated".toList)) should be("letsmakeitmorecomplicated".toList)
  }

}
