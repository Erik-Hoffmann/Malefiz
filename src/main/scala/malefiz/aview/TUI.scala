package malefiz
package aview

import controller.ControllerInterface
import util.Observer
import controller.State
import model.{Field, Colors}
import scala.io.StdIn.readLine
import scala.util.{Try, Success, Failure}

class TUI(controller: ControllerInterface) extends Observer:
  val eol: String = sys.props("line.separator")
  controller.add(this)

  def output(): Unit =
    println(controller.getBoard.toString + s"$eol Player " + controller.currentPlayer.toString + Colors.default + s" do your move!$eol")

  def startMenu(): Unit =
    readLine(s"Please select an option:$eol(q)uit, (s)tart game$eol") match
      case "q" => System.exit(0)
      case "s" => controller.dice();output();inputOption()
      case _ => startMenu()

  def inputOption(): Unit =
    readLine(s"Please select an option:$eol(q)uit, do(t)urn, (u)ndo, (r)edo$eol") match
      case "q" => System.exit(0)
      case "t" => controller.turn();inputTargetField();inputOption()
      case "u" => inputOption();
      case "r" => inputOption();
      case _ => inputOption()

  def inputTargetField(): Unit =
    toIntTuple(readLine(s"<x,y>:$eol")) match
      case Success(t) => controller.inputExecute(t._1, t._2)
      case Failure(_) => println("Invalid input! Try again!"); inputTargetField()

  def toIntTuple(input: String): Try[(Int,Int)] =
      Try {input.replace(" ", "").split(",").map(i => i.toInt) match {case Array(x,y) => (x,y)}}

  override def update(): Unit =
    controller.state.match
      case State.Output => output()
      case State.Failure => println(s"Something went wrong, please try again!$eol")
      case State.ChoosePeg => println(s"Please enter coordinates of the peg you want to move!$eol"+"Your pegs are here: [" + controller.currentPlayer.getPegs.map(peg => "("+peg.x+", "+peg.y+")").mkString(", ")+"]")
      case State.ChosePegSuccess => println(s"Peg chosen successfully!$eol")
      case State.ChooseDest => println(s"Please enter destination coordinates$eol you diced: "+controller.diced+s" and chose a peg at "+controller.location.toString+" "+eol)
      case State.ChooseDestSuccess => println(s"Destination chosen successfully!$eol")
      case State.ChooseBlocker =>

  def run(): Unit = startMenu()