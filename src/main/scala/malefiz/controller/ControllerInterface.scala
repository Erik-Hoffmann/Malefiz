package malefiz
package controller

import aview.TUI
import model.GameBoardInterface
import model.Player
import model.Field
import model.Peg
import model.Colors
import util.Observable

trait ControllerInterface extends Observable:

  val state: State = State.Output
  var currentPlayer: Player = Player(Colors.red)
  var diced: Int = 0

  def loadSavedGame(): Unit
  def saveGame(): Unit
  def undo(): Unit
  def redo(): Unit
  def newGame(players: Int): Unit
  def setNumPlayers(players: Int): Unit
  def getBoard: GameBoardInterface
  def endTurn(): Unit
  def inputExecute(x: Int, y: Int): Unit
  def newGame(): Unit
  def setPegTarget(x: Int, y: Int): Boolean
  def movePeg(newStone: Peg): Unit
  def getPeg: Peg
  def setTargetField(newField: Field): Unit
  def getTargetField: Field
