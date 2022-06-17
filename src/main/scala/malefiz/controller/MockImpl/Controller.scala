package malefiz
package controller.MockImpl

import controller.BaseImpl.Turn
import controller.ControllerInterface
import model.GameboardInterface

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
