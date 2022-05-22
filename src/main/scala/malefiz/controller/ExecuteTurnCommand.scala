package malefiz
package controller

import model.Gameboard
import model.Turn
import model.Stone
import util.Command
import util.UndoManager

class ExecuteTurnCommand(turn: Turn, controller: Controller) extends Command[Gameboard]:
  override def doStep(): Unit = controller.field.put(turn.newStone, turn.x, turn.y)
  override def undoStep(): Unit = controller.field.put(turn.newStone, turn.x, turn.y)
  override def redoStep(): Unit = controller.field.put(turn.newStone, turn.x, turn.y)
