package malefiz
package aview

import controller.ControllerInterface
import util.Observer
import controller.State
import model.{Field, Colors}
import model.Colors._
import scala.io.StdIn.readLine
import scala.util.{Try, Success, Failure}

class TUI(controller: ControllerInterface) extends Observer:
  val eol: String = sys.props("line.separator")
  controller.add(this)

  def printBoard(): Unit =
    println(s"${controller.getBoard.toString}$eol Player ${controller.currentPlayer.toString} do your move!$eol")

  def startMenu(): Unit =
    readLine(s"Please select an option:$eol(q)uit, (s)tart game, (l)oad$eol") match
      case "q" => System.exit(0)
      case "s" => controller.dice();printBoard();inputOption()
      case "l" => controller.loadSavedGame(); controller.dice(); printBoard(); inputOption()
      case _ => startMenu()

  def inputOption(): Unit =
    readLine(s"Please select an option:$eol(q)uit, do(t)urn, (u)ndo, (r)edo, (y)ield, (s)et a Peg, (save) game$eol") match
      case "q" => System.exit(0)
      case "t" => if (controller.diced == 6 && controller.currentPlayer.pegs.length < controller.currentPlayer.numPegs )controller.newPeg();controller.turn();inputTargetField();inputOption()
      case "u" => controller.undo(); printBoard(); inputOption()
      case "r" => controller.redo(); printBoard(); inputOption()
      case "y" =>controller.state = State.MoveComplete; controller.inputExecute(0,0); inputOption()
      case "s" => controller.state = State.Set; inputOption()
      case "save" => controller.saveGame(); inputOption()
      case _ => inputOption()

  def inputTargetField(): Unit =
    toIntTuple(readLine(s"<x,y>:$eol")) match
      case Success(t) => controller.inputExecute(t._1, t._2)
      case Failure(_) => println("Invalid input! Try again!"); inputTargetField()
  
  def toIntTuple(input: String): Try[(Int,Int)] =
      Try {input.replace(" ", "").split(",").map(i => i.toInt) match {case Array(x,y) => (x,y)}}

  override def update(): Unit =
    controller.state match
      case State.Output => printBoard()
      case State.Failure => println(s"$red${controller.errMessage}$default$eol")
      case State.ChoosePeg => println(s"Please enter coordinates of the peg you want to move!$eol Your pegs are here: [${controller.currentPlayer.getPegs.map(peg => "("+peg.y+", "+peg.x+")").mkString(", ")}]")
      case State.ChosePegSuccess => println(s"$green Peg chosen successfully!$default$eol")
      case State.ChooseDest => println(s"Please enter destination coordinates$eol you diced: ${controller.diced} and chose a peg at ${controller.location.toString}$eol")
      case State.ChooseDestSuccess => println(s"$green Destination chosen successfully!$default$eol")
      case State.MoveSuccess => println(s"$green Move Successful!$default$eol")
      case State.Win => println(s"${controller.currentPlayer.toString} won the game!")
      case State.ChooseBlockerTarget => println(s"You need to move a Blocker, select a destination!$eol")
      case State.MoveComplete => println("Nice!!")
      case State.Set =>

  def run(): Unit = startMenu()
