package malefiz
package model

import java.awt.Color

case class Player(color: Colors):
  val numPegs: Int = 5
  val pegs: Array[Field] = Array.ofDim[Field](5)
  def getPegs: Array[Field] = pegs.filter(_!=null)
  def getPegByPos(x: Int, y: Int): Field = getPegs.filter(x == _.x).filter(y == _.y)(0)
  override def toString: String = ""+color+"\uE008"
