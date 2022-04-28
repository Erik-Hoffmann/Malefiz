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
//    inputLoop()

  override def update(): Unit = println(controller.field.toString)

//  def inputLoop(): Unit =
//    var i: Int = 0
//    for (i <- 0 to 1) {
//      analyseInput(readLine) match
//        case None       => i = 0
//        case Some(move) => controller.doAndPublish(controller.put, move)
//    }