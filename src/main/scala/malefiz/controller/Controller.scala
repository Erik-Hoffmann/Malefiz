package malefiz
package controller

import util.Command
import util.Observable
import util.UndoManager
import model.Gameboard
import model.Move

case class Controller(var field: Gameboard) extends Observable:
  val undoManager = new UndoManager[Gameboard]
  def doAndPublish(doThis: Int => Gameboard, dice: Int): Unit =
    field = doThis(dice)
    notifyObservers
  def doAndPublish(doThis: => Gameboard): Unit =
    field = doThis
    notifyObservers
  
  def put(dice: Int): Gameboard = undoManager.doStep(field, new DiceCommand(dice))
  def undo: Gameboard = undoManager.undoStep(field)
  def redo: Gameboard = undoManager.redoStep(field)
  override def toString: String = field.toString
