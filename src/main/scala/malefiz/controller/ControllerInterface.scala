package malefiz
package controller

import controller.BaseImpl.Turn
import model.GameboardInterface
import util.Observable

trait ControllerInterface(board: GameboardInterface) extends Observable {
  
  var field : GameboardInterface = board
  var diced: Int = 0
  
  def nextPlayer(): Unit
  def firstPlayer(): Unit
  def legalMove(turn: Turn): Boolean
  def put(turn: Turn): Unit
  def movePeg(turn: Turn): Unit
  def undo(): Unit
  def redo(): Unit
  def dice():Unit
}
