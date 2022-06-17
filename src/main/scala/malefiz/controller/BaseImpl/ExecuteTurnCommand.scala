package malefiz
package controller.BaseImpl

import controller.BaseImpl.Controller
import model.BaseImpl.Stone
import model.GameboardInterface
import util.Command

import util.{Command, UndoManager}

class ExecuteTurnCommand(turn: Turn, controller: Controller) extends Command[GameboardInterface]:
  override def noStep(field: GameboardInterface): GameboardInterface = field
  override def doStep(field: GameboardInterface): GameboardInterface = controller.field.switchPos(turn.srcPos, turn.destPos)

  override def undoStep(field: GameboardInterface): GameboardInterface = {
    if(field.playerList.indexOf(field.currentPlayer) == 0) {
      field.currentPlayer = field.playerList.last
    } else {
      field.currentPlayer = field.playerList(field.playerList.indexOf(field.currentPlayer)-1)
    }
    if(turn.srcPos.isEmpty) {
      controller.field.undoPutPeg(turn.destPos)
    } else {
      controller.field.switchPos(Option.apply(turn.destPos), turn.srcPos.get)
    }
  }
  override def redoStep(field: GameboardInterface ): GameboardInterface = controller.field.switchPos(turn.srcPos, turn.destPos)
