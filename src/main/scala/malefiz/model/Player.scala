package malefiz
package model

case class Player(color: Colors):
  val numPegs: Int = 5
  val pegs: Array[Peg] = Array.ofDim[Peg](1)
