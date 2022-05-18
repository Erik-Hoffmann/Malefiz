package malefiz
package aview

import controller.Controller
import model.Move
import model.Direction
import model.Peg
import scala.io.StdIn.readLine
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run(): Unit =
    println(controller.field.toString)
    inputLoop()

  override def update: Unit = println(controller.field.toString)

  def inputLoop(): Unit =
    analyseInput(readLine) match
      case None       =>
      case Some(move) => controller.doAndPublish(controller.put, move)
    inputLoop()

  def analyseInput(input: String): Option[Move] =
    input match
      case "q" => None
      case "z" => controller.doAndPublish(controller.redo); None
      case "y" => controller.doAndPublish(controller.undo); None
//      case n: Int => {
//        val num = n.toInt
        /*
        * if (num < currentPlayer.stoneObs.length) {
            directionAnalyser(readLine)
          }
        * */
//      }
      case _ => Some(Move(controller.field.playerList(0).stoneObs(0), 0,0))

  def directionAnalyser(input: String): Array[Direction] =
    val directions = input.split("")
    directions.map(d =>
    d match
      case "r" => Direction.Right
      case "l" => Direction.Left
      case "u" => Direction.Up
      case "d" => Direction.Down
      case _   => Direction.Empty
    )