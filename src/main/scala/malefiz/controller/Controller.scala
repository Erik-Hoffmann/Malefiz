package malefiz
package controller

import com.google.inject.{Guice, Injector}
import scala.util.Random
import model.{Field, GameBoard, GameBoardInterface, Peg, Player, Ground}

case class Controller(numPlayers: Int) extends ControllerInterface:
  var gameBoard: GameBoardInterface = GameBoard(numPlayers).buildGame
  currentPlayer = gameBoard.players(0)

  def getBoard: GameBoardInterface = gameBoard
  def endTurn(): Unit = ???
  def getPeg: Peg = ???
  def getTargetField: Field = ???
  def inputExecute(x: Int, y: Int): Unit = ???
  def loadSavedGame(): Unit = ???
  def movePeg(newStone: Peg): Unit = ???
  def newGame(players: Int): Unit = ???
  def newGame(): Unit = ???
  def redo(): Unit = ???
  def saveGame(): Unit = ???
  def setNumPlayers(players: Int): Unit = ???
  def setPegTarget(x: Int, y: Int): Boolean = ???
  def setTargetField(newField: Field): Unit = ???
  def undo(): Unit = ???
  def dice(): Unit = diced = Random.nextInt(6)+1
