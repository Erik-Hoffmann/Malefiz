package malefiz
package controller

import model.Gameboard
import model.Turn
import model.Stone
import util.Command
import util.UndoManager

class ExecuteTurnCommand(turn: Turn, controller: Controller) extends Command[Gameboard]:
  override def doStep(): Unit = 
//    turn.oldField = controller.field(turn.move.destX)(turn.move.destY)
    controller.field.put(turn)
  override def undoStep(): Unit = controller.field.put(Stone("freeField"), turn.move.destX, turn.move.destY)
  override def redoStep(): Unit = controller.field.put(turn.move.stone, turn.move.destX, turn.move.destY)
