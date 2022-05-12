package malefiz
package aview

import controller.Controller
import model.Move
import model.Token
import model.Direction
import scala.io.StdIn.readLine
import util.Observer

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run(): Unit =
    println(controller.field.toString)
//    inputLoop()

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
      case _ => {
        val chars = input.toCharArray
//        val direction = chars(0) match
//          case 'r' => Direction.Right
//          case 'l' => Direction.Left
//          case 'u' => Direction.Up
//          case 'd' => Direction.Down
//          case _   => Direction.Empty
        val x = chars(1).toString.toInt
        val y = chars(2).toString.toInt
        Some(Move(Token("field"), x, y))
      }