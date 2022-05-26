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

  override def undoStep(field: Gameboard): Gameboard = {
    if(field.playerList.indexOf(field.currentPlayer) == 0) {
      field.currentPlayer = field.playerList.last
    } else {
      field.currentPlayer = field.playerList(field.playerList.indexOf(field.currentPlayer)-1)
    }
    if(turn.srcPos.isEmpty) {
      return controller.field.undoPutPeg(turn.destPos)
    }
    return controller.field.switchPos(Option.apply(turn.destPos), turn.srcPos.get)
  }
  override def redoStep(field: Gameboard ): Gameboard = controller.field.switchPos(turn.srcPos, turn.destPos)
