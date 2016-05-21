package recfun

import scala.annotation.tailrec

object Week1 {

  def ?!?!(msg: => String): Nothing = throw new IllegalStateException(s"Should not happen: $msg")

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r)
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def loop(chars: List[Char], bracketStack: List[Char]): Boolean = {

      chars match {
        case Nil => bracketStack.isEmpty
        case '(' :: tailChar => loop(tailChar, '(' :: bracketStack)
        case ')' :: tailChar => bracketStack match {
          case Nil => false
          case '(' :: tailStack => loop(tailChar, tailStack)
          case ')' :: tailStack => false
          case c => ?!?!(s"unexpected char in stack $c")
        }
        case _ :: tailChar => loop(tailChar, bracketStack)
      }
    }

    loop(chars, Nil)
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    coins match {
      case coin :: other =>
        val possibleCounts = money / coin

        (0 to possibleCounts).map(count => {
          countChange(money - count * coin, other)
        }).sum

      case Nil =>
        if (money == 0) 1 else 0
    }
  }
}
