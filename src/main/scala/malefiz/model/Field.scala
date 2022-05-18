package malefiz.model

case class Field(x:Int, y: Int, stone: Stone) extends Ground():
  def isFree: Boolean = stone == FreeField
  override def toString: String = stone.toString
