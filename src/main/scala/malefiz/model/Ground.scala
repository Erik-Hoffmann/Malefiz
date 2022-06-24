package malefiz
package model

abstract class Ground():
  val stone: Stone = Empty()
  def getStone: String = stone.toString
  def isFree: Boolean = true
