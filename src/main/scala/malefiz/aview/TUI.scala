package malefiz
package aview

import controller.ControllerInterface
import malefiz.controller.BaseImpl.Turn
import malefiz.model.BaseImpl.{Direction, Gameboard}

import scala.io.StdIn.readLine
import util.Observer

import java.util.Scanner
import scala.util.{Failure, Success, Try}

class TUI(cont: ControllerInterface) extends Observer:
  cont.add(this)
  println(cont.field.toString)

  def run(): Unit =
    doTurn()


  override def update(): Unit = println(cont.field.toString)

  def doTurn(): Unit =
    val input = readLine("Command to Execute: ")
    input match
      case "q" => System.exit(0);
      case "p" => cont.field.playerList.foreach(p => p.pegs.foreach(pos => println(pos.toString())))
      case "s" => cont.put(new Turn(Option.empty,inputPosition))
      case "z" => cont.undo()
      case "r" => cont.redo()

    if cont.field.playerList.indexOf(cont.field.currentPlayer) + 1< cont.field.playerList.length
      then
      cont.nextPlayer()
      doTurn()
      else
      cont.firstPlayer()
      doTurn()
    //directionAnalyser(choosePeg(controller.currentPlayer), readLine)

  //def choosePeg(player: Player): Unit = {} // Field =
    //controller.field.getField(player.pegs(validateNumber(readLine).getOrElse(0)))



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
  def inputPosition: (Int, Int) = {
      val sc = new Scanner(System.in)
      val pos1 = readLine("Position for Peg x: ").toInt
      val pos2 = readLine("y: ").toInt
      return (pos1,pos2)
    }
  def validateNumber(x: String): Try[Int] = Try { x.toInt } // Try Monad ?
