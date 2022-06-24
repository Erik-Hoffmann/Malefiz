package malefiz
package controller

import com.google.inject.{Guice, Injector}
import scala.util.Random
import model.{Field, GameBoard, GameBoardInterface, Peg, Player, FreeField, Ground, Empty}

case class Controller(numPlayers: Int) extends ControllerInterface:
  var gameBoard: GameBoardInterface = GameBoard(numPlayers).buildGame
  currentPlayer = gameBoard.players(0)
  var playersPeg: Field = currentPlayer.pegs(0)
  var playersTarget: Field = Field(0,0, Empty())

  def getBoard: GameBoardInterface = gameBoard
  def turn(): Unit =
    notifyObservers()
  def inputExecute(x: Int, y: Int): Unit =
    state match
      case State.ChoosePeg =>
        if (currentPlayer.getPegs.map(_.getCoords).contains((x,y)))
          state = State.ChosePegSuccess
          notifyObservers()
          playersPeg = currentPlayer.getPegByPos(x,y)
          location = (x,y)
          state = State.ChooseDest
        else
          state = State.Failure
          notifyObservers()
          state = State.ChoosePeg
      case State.ChooseDest =>
        println(gameBoard.board(x)(y).toString)
        if (validateTargetField(x,y))
          state = State.ChooseDestSuccess
          playersTarget =
          movePeg()
          state = State.Output
          notifyObservers()


  def loadSavedGame(): Unit = ???
  def movePeg(): Unit = ??? // TODO: dis not working :(
//    val temp = playersPeg
//    gameBoard.board(playersPeg.x)(playersPeg.y) = Field(playersPeg.x, playersPeg.y, FreeField())
//    gameBoard.board(playersTarget.x)(playersTarget.y) = playersPeg
  def getTargetField(x: Int, y: Int): Field = gameBoard.board(x)(y).asInstanceOf[Field]

  def redo(): Unit = ???
  def saveGame(): Unit = ???
  def validateTargetField(x: Int, y: Int): Boolean = x>=0 && y>=0 && x<gameBoard.width && y<gameBoard.height && gameBoard.board(x)(y).isFree
  def undo(): Unit = ???
  def dice(): Unit = diced = Random.nextInt(6)+1
