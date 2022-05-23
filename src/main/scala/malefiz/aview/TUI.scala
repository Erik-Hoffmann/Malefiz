package malefiz
package aview

import controller.Controller
import model.Turn
import model.Direction
import model.Gameboard
import scala.io.StdIn.readLine
import util.Observer

import scala.util.{Try, Success, Failure}

class TUI(controller: Controller) extends Observer:
  controller.add(this)
  def run(): Unit =
    doTurn()

  
  override def update(): Unit = println(controller.field.toString)

  def doTurn(): Unit =
    controller.dice()
    if controller.field.playerList.indexOf(controller.field.currentPlayer) < controller.field.playerList.length
      then controller.nextPlayer()
      else controller.firstPlayer()
    //directionAnalyser(choosePeg(controller.currentPlayer), readLine)

  //def choosePeg(player: Player): Unit = {} // Field =
    //controller.field.getField(player.stoneObs(validateNumber(readLine).getOrElse(0)))



  def analyseInput(input: String): Option[Int] = Some(0)
//    input match
//      case "q" => System.exit(0); None
//      case "z" => controller.redo(); controller.doAndPublish(); None
//      case "y" => controller.undo(); controller.doAndPublish(); None
//      case "n" => controller.doAndPublish(controller.field.buildBoard()); None
//      case "u" => controller.doAndPublish(controller.field.exampleUpdateBoard()); None

  //def directionAnalyser(pegField: Field, input: String): Unit = {}//Array[Direction] =
    //input.map(d => if !directionValid(pegField, input) then directionAnalyser(pegField, input) else controller.field.movePeg(pegField, input))

  //def directionValid(currentField: Field, direction: String): Unit = {} // Option[Direction] =
    /*
    direction match
      case "r" => if controller.field.getField(currentField.x + 1, currentField.y).free() then Direction.Right else None
      case "l" => if controller.field.getField(currentField.x - 1, currentField.y).free() then Direction.Left else None
      case "u" => if controller.field.getField(currentField.x, currentField.y + 1).free() then Direction.Up else None
      case "d" => if controller.field.getField(currentField.x, currentField.y - 1).free() then Direction.Down else None
      case _ => println("Fehler!"); None
    */

  def validateNumber(x: String): Try[Int] = Try { x.toInt } // Try Monad ?
