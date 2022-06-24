package malefiz
package controller.BaseImpl

import model.GameboardInterface
import util.Command

class ExecuteYieldCommand(controller: Controller) extends Command[GameboardInterface]{
  override def noStep(field: GameboardInterface): GameboardInterface = field
  override def doStep(field: GameboardInterface): GameboardInterface = {
    
    controller.nextPlayer()
    controller.dice()
    controller.field
  }
  override def undoStep(field: GameboardInterface): GameboardInterface = {
    println("Player field: "+ field.currentPlayer)
    println("Player controller.Field: "+controller.field.currentPlayer)
    field.currentPlayer = controller.field.currentPlayer
    field.dice = controller.field.dice
    controller.field
  }
  override def redoStep(field: GameboardInterface ): GameboardInterface = {
    controller.nextPlayer()
    controller.dice()
    controller.field
  }

}
