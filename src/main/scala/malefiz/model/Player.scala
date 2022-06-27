package malefiz
package model

import Colors.default
import java.awt.Color

case class Player(color: Colors, startField: (Int, Int)):
  val numPegs: Int = 5
  var pegs: Array[Field] = Array.ofDim[Field](5)
  def updatePeg(source: Field, dest: Field): Unit = pegs(pegs.indexOf(source)) = dest
  def getPegs: Array[Field] = pegs.filterNot(_==null)
  def getPegByPos(x: Int, y: Int): Option[Field] = if (!getPegs.exists(_.getCoords.equals((x, y)))) None else Option(getPegs.filter(_.getCoords.equals((x,y)))(0))
  override def toString: String = ""+color+"\uE008"+default
