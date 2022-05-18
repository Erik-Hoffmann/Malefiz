package malefiz
package controller

import util.Command
import util.Observable
import util.UndoManager
import model.Gameboard
import model.Move

case class Controller(var field: Gameboard) extends Observable:
  val undoManager = new UndoManager[Gameboard]
  def doAndPublish(doThis: Move => Gameboard, move: Move): Unit =
    field = doThis(move)
    notifyObservers
  def doAndPublish(doThis: => Gameboard): Unit =
    field = doThis
    notifyObservers
  def put(move: Move): Gameboard = undoManager.doStep(field, PutCommand(move))
  def undo: Gameboard = undoManager.undoStep(field)
  def redo: Gameboard = undoManager.redoStep(field)
  override def toString: String = field.toString