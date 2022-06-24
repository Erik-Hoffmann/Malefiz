package malefiz
package model

import java.awt.Color

case class Player(color: Colors):
  val numPegs: Int = 5
  val pegs: Array[Field] = Array.ofDim[Field](5)
  override def toString: String = ""+color+"\uE008"
