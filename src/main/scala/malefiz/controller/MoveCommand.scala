package malefiz
package controller

import model.{Field, GameBoard, Ground, Player}
import util.Command


class MoveCommand(controller: Controller) extends Command[Controller]{
  val player: Player = controller.currentPlayer.copy()
  val playersPeg: Field = controller.playersPeg.copy()
  val playersTarget: Field = controller.playersTarget.copy()
  val diceRoll: Int = controller.diced

  def noStep(controller: Controller): Controller = controller

  def doStep(controller: Controller): Controller = controller

  def undoStep(controller: Controller): Controller =
    player.updatePeg(playersPeg, playersTarget)
    val temp = playersPeg.stone
    playersPeg.stone = playersTarget.stone
    playersTarget.stone = temp
    controller.currentPlayer = player
    controller.diced = diceRoll
    controller

  def redoStep(controller: Controller): Controller =
    player.updatePeg(playersPeg, playersTarget)
    val temp = playersPeg.stone
    playersPeg.stone = playersTarget.stone
    playersTarget.stone = temp
    controller.currentPlayer = player
    controller.playerRotation()
    controller.diced = diceRoll
    controller
}
