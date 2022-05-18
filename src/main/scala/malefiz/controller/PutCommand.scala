package malefiz
package controller

import model.Gameboard
import model.Move
import model.Stone
import model.Empty
import util.Command
import util.UndoManager

class PutCommand(move: Move) extends Command[Gameboard]:
  override def noStep(field: Gameboard): Gameboard = field
  override def doStep(field: Gameboard): Gameboard = field.put(move.stone, move.x, move.y)
  override def undoStep(field: Gameboard): Gameboard = field.put(Empty(), move.x, move.y)
  override def redoStep(field: Gameboard): Gameboard = field.put(move.stone, move.x, move.y)
