package malefiz
package controller

import util.Command
import util.Observable
import util.UndoManager
import model.Gameboard
import model.Turn
import model.Player
import State._

case class Controller(var field: Gameboard) extends Observable:

  val undoManager = new UndoManager[Gameboard]
  var currentPlayer: Player = field.playerList.last
  var state: State = Output
  var diced: Int = 0

  def nextPlayer(): Unit =
    currentPlayer = field.playerList(field.playerList.indexOf(currentPlayer)+1)
    
  def firstPlayer(): Unit =
    currentPlayer = field.playerList(0)
      
  def doAndPublish(doThis: Int => Gameboard, turn: Turn): Unit =
    field = doThis(turn)
    notifyObservers()

  def doAndPublish(doThis: => Gameboard): Unit =
    field = doThis
    notifyObservers()

  def doAndPublish(): Unit =
    notifyObservers()

  def put(turn: Turn): Unit = undoManager.doStep(new ExecuteTurnCommand(turn, this))

  def undo(): Unit =
    undoManager.undoStep()
    val lastState = state

  def redo(): Unit =
    undoManager.redoStep()


  override def toString: String = field.toString

  def dice():Unit = diced = scala.util.Random.nextInt(6)+1
