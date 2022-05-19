package malefiz
package aview

import controller.Controller
import model.Move
import model.Direction
import scala.io.StdIn.readLine
import util.Observer

import scala.util.{Try, Success, Failure}

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run(): Unit =
    println(controller.field.toString)
    inputLoop()

  override def update: Unit = println(controller.field.toString)

  def inputLoop(): Unit =
    analyseInput(readLine, Dicer.dice) match // todo: move dicing somewhere else
      case None       => println("y: undo | z: redo | d: put | q: print this")
      case Some(move) => controller.doAndPublish(controller.put, move)
    inputLoop()

  def analyseInput(input: String, diced: Int): Option[Int] =
    input match
      case "q" => None
      case "z" => controller.doAndPublish(controller.redo); None
      case "y" => controller.doAndPublish(controller.undo); None
      case "d" => controller.put(diced); None
      case _ => Some(0)

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

  object Dicer {
    def dice:Int = manageDice(scala.util.Random.nextInt(6)+1)

    def manageDice(diced: Int):Int =
      println("diced: " + diced)
      if diced == 6 then choose(readLine, diced) else diced

    def choose(input: String, diced: Int):Int =
      println("input 'n' for a new peg!")
      input match
        case "n" => // currentPlayer.stoneObs + currentPlayer.startPos
           diced
        case _ => dice + diced
  }

  def validateNumber(x: String): Try[Int] = Try { x.toInt } // Try Monad ?