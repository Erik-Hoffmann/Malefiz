package malefiz.controller.MockImpl

import malefiz.controller.BaseImpl.Turn
import malefiz.controller.ControllerInterface
import malefiz.model.GameboardInterface

class Controller(board : GameboardInterface) extends ControllerInterface(board){

  def nextPlayer(): Unit = {}
  def firstPlayer(): Unit ={}
  def legalMove(turn: Turn): Boolean = false
 def movePeg(turn: Turn): Unit = {}
  def put(turn: Turn): Unit ={}
  def undo(): Unit ={}
  def redo(): Unit ={}
  def dice():Unit = {}
}
