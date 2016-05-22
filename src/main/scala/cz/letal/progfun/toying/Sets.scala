package cz.letal.progfun.toying

object Sets extends App {

  abstract class IntSet {
    def incl(x: Int): IntSet

    def contains(x: Int): Boolean

    def union(that: IntSet): IntSet
  }

  class NonEmpty(v: Int, left: IntSet, right: IntSet) extends IntSet {

    def this(v: Int) = this(v, Empty, Empty)

    override def incl(x: Int): IntSet =
      if (x < v) new NonEmpty(v, left incl x, right)
      else if (v < x) new NonEmpty(v, left, right incl x)
      else this

    override def contains(x: Int): Boolean =
      if (x < v) left.contains(x)
      else if (v < x) right.contains(x)
      else true

    override def union(that: IntSet): IntSet = {
      right.union(left.union(that.incl(v)))
    }

    override def toString: String = s"{$left$v$right}"
  }

  object Empty extends IntSet {
    override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

    override def contains(x: Int): Boolean = false

    override def union(that: IntSet): IntSet = that

    override def toString: String = "."
  }

  private val s1 = new NonEmpty(3).incl(4).incl(10).incl(6)
  private val s2 = new NonEmpty(1).incl(5).incl(12)
  println(s1)
  println(s2)
  println(s2.union(s1))

}
