package malefiz.controller

import malefiz.model.{GameboardInterface, Turn}
import malefiz.util.Observable

trait ControllerInterface(board: GameboardInterface) extends Observable {
  
  var field : GameboardInterface = board
  
  def nextPlayer(): Unit
  def firstPlayer(): Unit
  def put(turn: Turn): Unit
  def undo: Unit
  def redo(): Unit
  def dice():Unit
}
