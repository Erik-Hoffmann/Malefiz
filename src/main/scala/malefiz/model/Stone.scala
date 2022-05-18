package malefiz.model

abstract class Stone(sort: String):
  override def toString: String = sort

case class Peg(position: Ground) extends Stone(" \uE008 ")

case class FreeField() extends Stone(" □ ")

case class Blocker() extends Stone(" ■ ")

case class Empty() extends Stone("   ")

object Stone {
  def getStone(kind: String):Stone = {
    kind match
      case "freefield" => new FreeField
      case "blocker" => new Blocker
      case "empty" => new Empty
  }
  def getStone(position: Ground): Peg = new Peg(position) {}
}