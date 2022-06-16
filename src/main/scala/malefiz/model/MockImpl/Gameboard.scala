package malefiz.model.MockImpl

import malefiz.model.BaseImpl.{Player, Stone}
import malefiz.model.GameboardInterface
import malefiz.model.BaseImpl.Colors

class Gameboard(players : Int) extends GameboardInterface(players){

  def dimensions(players: Int): (Int, Int) = (1,1)

  def createPlayers: Array[Player] = Array(new Player(Colors.red, 0,0))

  def buildBoard(): GameboardInterface = this

  def put(stone: Stone, x:Int,y:Int): GameboardInterface = this

  def switchPos(srcPos: Option[(Int, Int)], destPos:(Int,Int)): GameboardInterface = this
  def undoPutPeg(destPos :(Int, Int)): GameboardInterface = this
  def exampleUpdateBoard(): GameboardInterface = this
  def legalMove(src:(Int,Int), dest: (Int,Int), countMoves: Int): Boolean = false
  def removeBlocker(pos :(Int,Int)): GameboardInterface = this
  def sendPegHome(pos: (Int, Int)): GameboardInterface = this
  def checkPeg(pos :(Int,Int)): Boolean = false
  def checkEmpty(pos :(Int,Int)): Boolean = false
  def checkBlocker(pos :(Int,Int)): Boolean = false
  def checkFreeField(pos :(Int,Int)): Boolean = false
}
