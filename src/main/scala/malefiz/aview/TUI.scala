package malefiz
package aview

import controller.Controller
import model.Tokens._
import scala.io.StdIn.readLine
import util.Observer

case class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run(): Unit =
    println(controller.field.toString)
//    Iterator.continually(inputLoop())

  override def update(): Unit = println(controller.field.toString)

//  def inputLoop(): Unit =
//      analyseInput(readLine) match
//        case None       =>
//        case Some(move) => controller.doAndPublish(controller.put, move)
    