package malefiz
package aview

import controller.ControllerInterface
import util.Observer
import controller.State

import scala.io.StdIn.readLine

class TUI(controller: ControllerInterface) extends Observer:

  controller.add(this)

  def output(): Unit =
    controller.getBoard.toString

  def inputTargetField(diced: Int): Array[Char] =
    val coords: Array[Char] = readLine("Enter target field " + diced + " fields from your position or nothing to skip your turn: ").toCharArray
    if (coords.length != 2 && coords.nonEmpty || !coords.forall(Character.isDigit)) {
      print("Invalid char(s) detected, please try again!")
      inputTargetField(diced)
    } else {
      coords
    }

  override def update(): Unit =
    controller.state.match
      case State.Output => output()
      case State.SetPlayer =>
      case State.ChoosePeg =>
      case State.DefineMoves =>
      case State.ChooseBlocker =>

  def run(): Unit = output()