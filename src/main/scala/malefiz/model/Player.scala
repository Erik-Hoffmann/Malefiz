package malefiz
package model

class Player(color: Colors, x: Int, y: Int) {
  val stoneNum = 5
  val stoneObs: Array[(Int, Int)] = Array[(Int, Int)]()
  val startField: (Int, Int) = (x,y)
}
