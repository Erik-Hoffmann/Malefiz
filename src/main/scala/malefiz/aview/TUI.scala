package malefiz
package aview

import controller.ControllerInterface
import util.Observer
import controller.State
import model.{Field, Colors}
import scala.io.StdIn.readLine

class TUI(controller: ControllerInterface) extends Observer:
  val eol: String = sys.props("line.separator")
  controller.add(this)

  def output(): Unit =
    println(controller.getBoard.toString + s"$eol Player " + controller.currentPlayer.toString + Colors.default + s" do your move!$eol You diced " + controller.diced + s"!$eol")

  def startMenu(): Unit =
    readLine(s"Please select an option:$eol(q)uit, (s)tart game$eol") match
      case "q" => System.exit(0)
      case "s" => controller.dice();output();controller.state = State.ChoosePeg
      case _ => startMenu()

  def inputOption(): Unit =
    readLine(s"Please select an option:$eol(q)uit, do(t)urn") match
      case "q" => System.exit(0)
      case "t" =>


  def inputTargetField(diced: Int): Array[Char] =
    val coords: Array[Char] = readLine(s"Enter target field $diced fields from your position or nothing to skip your turn:$eol").toCharArray
    if (coords.length != 2 && coords.nonEmpty || !coords.forall(Character.isDigit)) {
      print(s"Invalid char(s) detected, please try again!$eol")
      inputTargetField(diced)
    } else {
      coords
    }

  override def update(): Unit =
    controller.state.match
      case State.Output => output()
      case State.ChoosePeg =>
      case State.DefineMoves =>
      case State.ChooseBlocker =>

  def run(): Unit = startMenu()