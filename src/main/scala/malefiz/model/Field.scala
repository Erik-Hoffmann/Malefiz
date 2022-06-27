package malefiz
package model

case class EmptyGround(x: Int, y: Int) extends Ground():
  override def toString: String = "   "
  override def isFree: Boolean = false

case class Field(x: Int, y: Int, var stone: Stone) extends Ground():
  def getCoords: (Int, Int) = (x,y)
  override def isFree: Boolean = stone.isInstanceOf[FreeField] || stone.isInstanceOf[Blocker]
  def isBlocker: Boolean = stone.isInstanceOf[Blocker]
  override def toString: String = stone.toString
