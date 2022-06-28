package malefiz
package model

class Stone(sort: String):
  override def toString: String = sort

case class Peg(color: Colors) extends Stone(" \uE008 "):
  override def toString: String = "" + color + " \uE008 " + Colors.default

case class FreeField() extends Stone(" □ ")

case class Blocker() extends Stone(" ■ ")

case class Empty() extends Stone("   ")

