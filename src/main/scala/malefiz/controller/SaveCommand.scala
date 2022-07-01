package malefiz
package controller

import model.{Field, GameBoard, Ground, Player}
import util.Command

class SaveCommand(controller: Controller) extends Command[Controller] {
  var savedController: Controller = controller.copy()
  val player: Player = controller.currentPlayer.copy()
  val diceRoll: Int = controller.diced
  val location: (Int, Int) = controller.location.copy()

  def noStep(controller: Controller): Controller = controller
  def doStep(controller: Controller): Controller = controller
  def undoStep(controller: Controller): Controller =

    controller.currentPlayer = player
    controller.diced = diceRoll
    controller
  def redoStep(controller: Controller): Controller =

    controller.currentPlayer = player
    controller.diced = diceRoll
    controller
}
