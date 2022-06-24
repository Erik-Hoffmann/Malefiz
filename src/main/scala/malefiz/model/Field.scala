package malefiz
package model

case class EmptyGround(x: Int, y: Int) extends Ground():
  override def isFree: Boolean = false

case class Field(x: Int, y: Int, override val stone: Stone) extends Ground():
  def getCoords: (Int, Int) = (x,y)
  override def isFree: Boolean = stone.isInstanceOf[FreeField]
  override def toString: String = stone.toString
