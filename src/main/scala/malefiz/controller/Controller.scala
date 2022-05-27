package malefiz
package controller

import util.Command
import util.Observable
import util.UndoManager
import model.Gameboard
import model.Turn
import State._

case class Controller(var field: Gameboard) extends Observable:

  val undoManager = new UndoManager[Gameboard]
  var state: State = Output
  var diced: Int = 0
  field.buildBoard()

  def nextPlayer(): Unit = field.currentPlayer = field.playerList(field.playerList.indexOf(field.currentPlayer)+1)
    
  def firstPlayer(): Unit =
    field.currentPlayer = field.playerList(0)
      /*
  def doAndPublish(doThis: Int => Gameboard, turn: Turn): Unit =
//    field = doThis(turn)
    notifyObservers()

  def doAndPublish(doThis: => Gameboard): Unit =
    field = doThis
    notifyObservers()

  def doAndPublish(): Unit =
    notifyObservers()
*/
  def put(turn: Turn): Unit =
    undoManager.doStep(field, ExecuteTurnCommand(turn, this))
    notifyObservers()

  def undo: Unit =
    field = undoManager.undoStep(field)
    notifyObservers()


  def redo(): Unit =
    undoManager.redoStep(field)
    notifyObservers()


  override def toString: String = field.toString

  def dice():Unit = diced = scala.util.Random.nextInt(6)+1
