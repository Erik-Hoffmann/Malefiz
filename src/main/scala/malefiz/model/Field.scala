package malefiz
package model

case class EmptyGround() extends Ground():
  def isFree: Boolean = true

case class Field(x: Int, y: Int, stone: Stone) extends Ground() :
  def isFree: Boolean = stone.isInstanceOf[FreeField]
  override def toString: String = stone.toString
