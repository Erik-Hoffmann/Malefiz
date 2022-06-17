package malefiz
package model.BaseImpl

import model.BaseImpl.Colors
import scala.collection.mutable.ArrayBuffer

class Player(color: Colors, x: Int, y: Int) {
  val colour: Colors = this.color
  val stoneNum = 5
  var pegs: ArrayBuffer[(Int, Int)] = ArrayBuffer[(Int, Int)]()
  val startField: (Int, Int) = (x,y)
}
