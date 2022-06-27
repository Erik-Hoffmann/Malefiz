package malefiz
package controller

import model.{Field, GameBoard, Ground}
import util.Command

class SaveCommand(controller: Controller) extends Command[Controller] {
  var savedController = controller.copy()
  val player = controller.currentPlayer.copy()
  val diceRoll = controller.diced
  val location = controller.location.copy()

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
