package malefiz
package controller

import aview.TUI
import util.Observable
import model.{Field, GameBoardInterface, Player}
import play.api.libs.json.JsValue

import scala.xml.Node

trait ControllerInterface extends Observable:
  var currentPlayer: Player
  var state: State = State.ChoosePeg
  var location: (Int, Int) = (0,0)
  var diced: Int = 0
  var possibleMoves = new Array[(Int,Int)](0)
  var errMessage = ""

  def loadSavedGame(): Unit
  def saveGame(): Unit
  def dice(): Unit
  def undo(): Unit
  def redo(): Unit
  def getBoard: GameBoardInterface
  def turn(): Unit
  def inputExecute(x: Int, y: Int): Unit
  def movePeg(): Unit
  def getTargetField(x: Int, y: Int): Field
  def playerRotation(): Unit
  def isWon(x: Int, y: Int): Boolean
  def newPeg(): Unit
  def fromJson(js: JsValue, currentplayer:Int): ControllerInterface
  def fromXML(node :Node, currentplayer: Int): ControllerInterface
