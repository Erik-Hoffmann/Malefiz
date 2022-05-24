package malefiz
package controller

import model.Gameboard
import model.Turn
import model.Stone
import util.Command
import util.UndoManager

class ExecuteTurnCommand(turn: Turn, controller: Controller) extends Command[Gameboard]:
  override def noStep(field: Gameboard): Gameboard = field
  override def doStep(field: Gameboard): Gameboard = controller.field.switchPos(turn.srcPos, turn.destPos)
  override def undoStep(field: Gameboard): Gameboard = controller.field.switchPos(turn.srcPos, turn.destPos)
  override def redoStep(field: Gameboard ): Gameboard = controller.field.switchPos(turn.srcPos, turn.destPos)
