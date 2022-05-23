package malefiz.model

abstract class Stone(sort: String):
  def isFree: Boolean = false
  override def toString: String = sort

case class Peg() extends Stone(" \uE008 ")

case class FreeField() extends Stone(" □ "):
  override def isFree: Boolean = true

case class Blocker() extends Stone(" ■ ")

case class Empty() extends Stone("   ")

object Stone {
  def apply(kind: Fields): Stone =
    kind match
      case Fields.FreeField => new FreeField
      case Fields.Blocker => new Blocker
      case Fields.Empty => new Empty
      case Fields.Peg => new Peg
}

enum Fields():
  case FreeField
  case Blocker
  case Empty
  case Peg