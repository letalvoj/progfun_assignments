package cz.letal.progfun.toying

import scala.io.Source

object TelephoneNumbers extends App {

  def loadFile(path: String): Seq[Word] =
    Source.fromFile(path)
      .getLines()
      .map(_.toLowerCase.filter(_.isLetter))
      .toSeq

  val names: Seq[String] = loadFile("/usr/share/dict/propernames") ++ loadFile("/usr/share/dict/connectives")

  val mnem: Map[Char, String] = Map(
    '2' -> "abc",
    '3' -> "def",
    '4' -> "ghi",
    '5' -> "jkl",
    '6' -> "mno",
    '7' -> "pqrs",
    '8' -> "tuv",
    '9' -> "wxyz"
  ).withDefaultValue("")

  val inverseMnem: Map[Char, Char] = mnem.flatMap {
    case (num, str) => str.map(c => (c, num))
  }

  private val namesByNumberRepr: Map[String, Seq[String]] = names
    .groupBy(_.map(inverseMnem))

  type Word = String
  type Sentence = List[Word]

  def toSentence(number: Word): List[Sentence] = {
    if (number.isEmpty)
      return List(Nil)

    for {
      len <- (1 to number.length).toList
      (head, tail) = number.splitAt(len) if namesByNumberRepr contains head
      word <- namesByNumberRepr(head)
      rest <- toSentence(tail)
    } yield word :: rest
  }

  val number = namesByNumberRepr.keysIterator.take(5).mkString
  val numberValid = number.filter(c => mnem.contains(c))

  println(number + " - " + toSentence(number).map(_.mkString(" ")).mkString("\n"))


}
