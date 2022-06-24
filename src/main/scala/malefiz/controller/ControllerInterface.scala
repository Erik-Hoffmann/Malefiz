package malefiz
package controller

import aview.TUI
import model.{GameBoardInterface, Peg, Player, Field, Colors, Ground}
import util.Observable

trait ControllerInterface extends Observable:

  var state: State = State.ChoosePeg
  var currentPlayer: Player = Player(Colors.red)
  var location: (Int, Int) = (0,0)
  var diced: Int = 0

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
