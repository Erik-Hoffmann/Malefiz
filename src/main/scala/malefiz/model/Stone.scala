package malefiz.model

abstract class Stone(sort: String):
  def isFree: Boolean = false
  override def toString: String = sort

object Stone {
  case class Peg() extends Stone(" \uE008 ")

  case class FreeField() extends Stone(" □ "):
    override def isFree: Boolean = true

  case class Blocker() extends Stone(" ■ ")

  case class Empty() extends Stone("   ")

  def apply(kind: String): Stone =
    kind match
      case "freefield" => new FreeField
      case "blocker" => new Blocker
      case "empty" => new Empty
      case "peg" => new Peg
}