package malefiz.model.BaseImpl

case class Field(x: Int, y: Int, stone: Stone) extends Ground() :
  def isFree: Boolean = stone.isFree

  override def toString: String = stone.toString
