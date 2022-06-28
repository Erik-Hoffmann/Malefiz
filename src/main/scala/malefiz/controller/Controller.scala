package malefiz
package controller

import com.google.inject.{Guice, Injector}
import util.UndoManager

import scala.util.Random
import model.{Empty, Field, FreeField, GameBoard, GameBoardInterface, Ground, Peg, Player}

case class Controller(numPlayers: Int) extends ControllerInterface:
  
  var gameBoard: GameBoardInterface = GameBoard(numPlayers).buildGame
  var currentPlayer: Player = gameBoard.players(0)
  var playersPeg: Field = currentPlayer.pegs(0)
  var playersTarget: Field = Field(0,0, Empty())
  var blockerTarget: Field = Field(0,0,Empty())
  var blocking: Boolean = false
  var undoManager = new UndoManager[Controller]

  def getBoard: GameBoardInterface = gameBoard
  
  def turn(): Unit = notifyObservers()
  
  def inputExecute(x: Int, y: Int): Unit =
    state match
      case State.ChoosePeg =>
        if (currentPlayer.getPegs.map(_.getCoords).contains((x,y)))
          state = State.ChosePegSuccess; notifyObservers()
          playersPeg = currentPlayer.getPegByPos(x,y).get
          location = (x,y)
          state = State.ChooseDest
        else
          state = State.Failure; notifyObservers()
          state = State.ChoosePeg
      case State.ChooseDest =>
        if (validateTargetField(x,y))
          state = State.ChooseDestSuccess; notifyObservers()
          playersTarget = getTargetField(x,y)
          if (playersTarget.isBlocker)
            state = State.ChooseBlockerTarget;
          else
            moveComplete(x,y)
            undoManager.doStep(this, new MoveCommand(this))
        else
          state = State.Failure; notifyObservers()
          state = State.ChooseDest
      case State.ChooseBlockerTarget =>
        if (validateTargetField(x,y) && !getTargetField(x,y).isBlocker)
          blockerTarget = getTargetField(x,y)
          moveBlocker()
          state = State.MoveComplete
          undoManager.doStep(this, new SaveCommand(this))
          moveComplete(x,y)
        else state = State.Failure; notifyObservers()
        
      case State.Output =>
      case State.Failure =>
      case State.ChosePegSuccess =>
      case State.ChooseDestSuccess =>
      case State.MoveSuccess =>
      case State.Win =>
      case State.MoveComplete =>

  def playerRotation(): Unit =
    println("current Index: " + gameBoard.players.indexOf(currentPlayer))
    currentPlayer = if (gameBoard.players.indexOf(currentPlayer) == gameBoard.players.length-1) gameBoard.players(0) else gameBoard.players(gameBoard.players.indexOf(currentPlayer)+1)

  def loadSavedGame(): Unit = ???

  def moveComplete(y: Int, x: Int): Unit =
    movePeg()
    state = State.MoveSuccess; notifyObservers()
    if (isWon(x,y))
      state = State.Win; notifyObservers()
    else
      playerRotation()
      state = State.Output; notifyObservers()
      state = State.ChoosePeg


  def newPeg(): Unit =
    currentPlayer.pegs(currentPlayer.pegs.filterNot(_.equals(null)).length) = Field(currentPlayer.startField._1, currentPlayer.startField._2, Peg(currentPlayer.color))
    gameBoard.board(currentPlayer.startField._1)(currentPlayer.startField._2)

  def movePeg(): Unit =
    currentPlayer.updatePeg(playersPeg, playersTarget)
    val temp = playersPeg.stone
    playersPeg.stone = playersTarget.stone
    playersTarget.stone = temp

  def moveBlocker(): Unit =
    val temp = blockerTarget.stone
    blockerTarget.stone = playersTarget.stone
    playersTarget.stone = temp

  def getTargetField(y: Int, x: Int): Field = gameBoard.board(x)(y).asInstanceOf[Field]

  def validateTargetField(y: Int, x: Int): Boolean = x>=0 && y>=0 && y<gameBoard.width && x<gameBoard.height && gameBoard.board(x)(y).isFree
  
  def isWon(y: Int, x: Int): Boolean = y == 0 && x == gameBoard.width/2+1
  
  def dice(): Unit = diced = Random.nextInt(6)+1
  
  def undo(): Unit = undoManager.undoStep(this)

  def redo(): Unit = undoManager.redoStep(this)
  
  def saveGame(): Unit = ???
