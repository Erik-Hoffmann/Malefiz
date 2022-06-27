package malefiz
package controller

import malefiz.model.GameBoard
import util.Command

class SaveCommand(controller: Controller) extends Command[Controller] {
  var savedController = controller.copy()
  //val savedGameboard = controller.gameBoard.asInstanceOf[GameBoard].copy()
  def noStep(controller: Controller): Controller = controller
  def doStep(controller: Controller): Controller =
    savedController = controller.copy()
    controller
  def undoStep(controller: Controller): Controller =
    //savedController.gameBoard = savedGameboard
    savedController
  def redoStep(controller: Controller): Controller =
    //savedController.gameBoard = savedGameboard
    savedController
}
