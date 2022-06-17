package malefiz
package model

import model.BaseImpl.{Player, Stone}

trait GameboardInterface(players: Int) {

  val playerList: Array[Player] = createPlayers
  var currentPlayer: Player = playerList.last
  val (width, height) = dimensions(players)

//  val dice: Int


  def dimensions(players: Int): (Int, Int)

  def createPlayers: Array[Player]

  def buildBoard(): GameboardInterface

  def put(stone: Stone, x:Int,y:Int): GameboardInterface

  def switchPos(srcPos: Option[(Int, Int)], destPos:(Int,Int)): GameboardInterface
  def undoPutPeg(destPos :(Int, Int)): GameboardInterface
  def exampleUpdateBoard(): GameboardInterface
  def legalMove(src:(Int,Int), dest: (Int,Int), countMoves: Int): Boolean
  def removeBlocker(pos :(Int,Int)): GameboardInterface
  def sendPegHome(pos: (Int, Int)): GameboardInterface
  def checkPeg(pos :(Int,Int)): Boolean
  def checkEmpty(pos :(Int,Int)): Boolean
  def checkBlocker(pos :(Int,Int)): Boolean
  def checkFreeField(pos :(Int,Int)): Boolean

  //  def store(num: Int): GameboardInterface

}
