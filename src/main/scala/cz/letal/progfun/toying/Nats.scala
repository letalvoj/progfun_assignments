package cz.letal.progfun.toying

object Nats extends App{
  val n5 = Zero.successor().successor().successor().successor().successor()
  val n3 = Zero.successor().successor().successor()
  println(n5)
  println(n3)
  println(n3+n5)
  println(n5-n3)
}

abstract class Nat {
  def isZero(): Boolean

  def successor(): Nat = new Succ(this)

  def predecessor(): Nat

  def +(other: Nat): Nat

  def -(other: Nat): Nat
}

object Zero extends Nat {
  override def isZero(): Boolean = true

  override def +(other: Nat): Nat = other

  override def -(other: Nat): Nat = throw new IllegalStateException("There is no negative natural number")

  override def predecessor(): Nat = throw new IllegalStateException("There is no negative natural number")

  override def toString = s"0"
}

class Succ(n: Nat) extends Nat {

  override def isZero(): Boolean = false

  override def +(other: Nat): Nat = new Succ(n + other)

  override def -(other: Nat): Nat = {
    if (other.isZero())
      this
    else
      this.predecessor() - other.predecessor()
  }

  override def predecessor(): Nat = n


  override def toString = s"Succ($n)"
}