package malefiz.controller.mockimpl

import malefiz.controller.ControllerInterface
import malefiz.model.{Field, FreeField, GameBoard, GameBoardInterface, Peg, Player, Colors}

class Controller(numPlayers: Int) extends ControllerInterface {
  var currentPlayer: Player = Player(Colors.red,(0,0))
  def loadSavedGame(): Unit =  {}
  def saveGame(): Unit = {}
  def dice(): Unit = {}
  def undo(): Unit = {}
  def redo(): Unit = {}
  def getBoard: GameBoardInterface = GameBoard(numPlayers)
  def turn(): Unit = {}
  def inputExecute(x: Int, y: Int): Unit = {}
  def movePeg(): Unit = {}
  def getTargetField(x: Int, y: Int): Field = Field(0,0,FreeField())
  def playerRotation(): Unit = {}
  def isWon(x: Int, y: Int): Boolean  = false
  def newPeg(): Unit = {}
}
