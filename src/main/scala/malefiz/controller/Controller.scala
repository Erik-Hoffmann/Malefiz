package malefiz
package controller

import com.fasterxml.jackson.annotation.JsonValue
import com.google.inject.{Guice, Injector}
import malefiz.aview.gui3d.Direction
import util.UndoManager

import scala.util.Random
import model.fileIO.fileIOInterface
import model.{Empty, Field, FreeField, GameBoard, GameBoardInterface, Ground, Peg, Player}
import play.api.libs.json.{JsNumber, JsValue, Json}

import scala.xml.Node

case class Controller(numPlayers: Int) extends ControllerInterface:
  
  var gameBoard: GameBoardInterface = GameBoard(numPlayers).buildGame
  var currentPlayer: Player = gameBoard.players(0)
  var playersPeg: Field = currentPlayer.pegs(0)
  var playersTarget: Field = Field(0,0, Empty())
  var blockerTarget: Field = Field(0,0,Empty())
  var blocking: Boolean = false
  var undoManager = new UndoManager[Controller]

  val injector: Injector = Guice.createInjector(new GameModule)
  val serializer = injector.getInstance(classOf[fileIOInterface])


  def getBoard: GameBoardInterface = gameBoard
  
  def turn(): Unit = notifyObservers()
  
  def inputExecute(x: Int, y: Int): Unit =
    state match
      case State.ChoosePeg =>
        if (currentPlayer.getPegs.map(_.getCoords).contains((y,x)))
          location = (x,y)
          calculatePossibleMoves()
          state = State.ChosePegSuccess; notifyObservers()
          playersPeg = currentPlayer.getPegByPos(y,x).get
          state = State.ChooseDest
        else
          errMessage = "no player Peg there"
          state = State.Failure; notifyObservers()
          state = State.ChoosePeg
      case State.ChooseDest =>
        if (validatePlayerTargetField(x,y))
          state = State.ChooseDestSuccess; notifyObservers()
          playersTarget = getTargetField(x,y)
          if (playersTarget.isBlocker)
            state = State.ChooseBlockerTarget;
          else
            if (!playersTarget.isFree)
              println("go home scoundrel")
              sendPegHome(y,x)
            moveComplete(x,y)
            undoManager.doStep(this, new MoveCommand(this))
        else
          errMessage = "this Move is not possible"
          state = State.Failure; notifyObservers()
          state = State.ChooseDest
      case State.ChooseBlockerTarget =>
        if (validateTargetField(x,y) && !getTargetField(x,y).isBlocker)
          blockerTarget = getTargetField(x,y)
          moveBlocker()
          state = State.MoveComplete
          undoManager.doStep(this, new SaveCommand(this))
          moveComplete(x,y)
        else
          errMessage = "cant set blocker here"
          state = State.Failure; notifyObservers()
          state = State.ChooseBlockerTarget
      case State.Set =>
        if(diced ==6 )
          if (currentPlayer.getPegByPos(
            currentPlayer.startField._2 - 1, currentPlayer.startField._1).isEmpty)
            newPeg()
            playerRotation();dice()
            state = State.Output; notifyObservers()
            state = State.ChoosePeg
          else
            errMessage = "Startfield is already occupied"
            state = State.Failure; notifyObservers()
            state = State.ChoosePeg
        else
          errMessage = "you need to roll a 6 for this "
          state = State.Failure; notifyObservers()
          state = State.ChoosePeg
      case State.Output =>
      case State.Failure =>
      case State.ChosePegSuccess =>
      case State.ChooseDestSuccess =>
      case State.MoveSuccess =>
      case State.Win =>
      case State.MoveComplete =>
        playerRotation();dice()
        state = State.Output; notifyObservers()
        state = State.ChoosePeg

  def playerRotation(): Unit =
    currentPlayer = if (gameBoard.players.indexOf(currentPlayer) == gameBoard.players.length-1) gameBoard.players(0) else gameBoard.players(gameBoard.players.indexOf(currentPlayer)+1)

  def loadSavedGame(): Unit =
    val controller  = serializer.loads()
    currentPlayer = controller.currentPlayer
    gameBoard = controller.getBoard
    notifyObservers()

  def moveComplete(y: Int, x: Int): Unit =
    movePeg()
    state = State.MoveSuccess; notifyObservers()
    possibleMoves = new Array[(Int,Int)](0)
    if (isWon(x,y))
      state = State.Win; notifyObservers()
    else
      playerRotation();dice()
      state = State.Output; notifyObservers()
      state = State.ChoosePeg

  def calculatePossibleMoves() : Unit =
    if(location._1 == 0 && location._2 == 0) {}
    else
      calculateMove(location._1, location._2, diced, Direction.Down)

  def calculateMove(x: Int, y: Int , moves: Int, directionBefore: Direction): Unit =
    if (y - 1 >= 0 && checkField(x, y - 1, moves))
      calculateMove(x, y - 1, moves - 1, Direction.Up)
    if ( x + 1 < gameBoard.width  && checkField(x + 1, y, moves) && directionBefore != Direction.Left)
      calculateMove(x + 1, y, moves - 1, Direction.Right)
    if (x - 1 >= 0 && checkField(x - 1, y, moves) && directionBefore != Direction.Right)
      calculateMove(x - 1, y, moves - 1, Direction.Left)
    if (moves == 0)
      possibleMoves = possibleMoves :+ (x, y)


  def checkField(x: Int, y: Int, moves: Int): Boolean =
    if (!gameBoard.board(y)(x).isFree)
      for (player <- gameBoard.players)
        if (player.getPegs.map(_.getCoords).contains((y,x))) {
          return true
        }
      false
    else
      if (!gameBoard.board(y)(x).asInstanceOf[Field].isBlocker && moves > 0)
        return true
      else if (gameBoard.board(y)(x).asInstanceOf[Field].isBlocker && moves == 1)
        return true
      false

  def newPeg(): Unit =
    if (currentPlayer.getPegs.length <= currentPlayer.numPegs)
      val field = Field(currentPlayer.startField._2 - 1, currentPlayer.startField._1, Peg(currentPlayer.color))
      currentPlayer.pegs(currentPlayer.getPegs.length) = field
      gameBoard.board(currentPlayer.startField._2- 1 )(currentPlayer.startField._1 ) = field

  def movePeg(): Unit =
    currentPlayer.updatePeg(playersPeg, playersTarget)
    val temp = playersPeg.stone
    playersPeg.stone = playersTarget.stone
    playersTarget.stone = temp

  def moveBlocker(): Unit =
    val temp = blockerTarget.stone
    blockerTarget.stone = playersTarget.stone
    playersTarget.stone = temp

  def sendPegHome(x: Int,  y: Int): Unit =
    gameBoard.board(x)(y) = new Field(x,y, FreeField())
    playersTarget = gameBoard.board(x)(y).asInstanceOf[Field]
    for (player <- gameBoard.players)
      if (player.getPegs.map(_.getCoords).contains((y,x)))
        player.removePeg(x, y)

  def getTargetField(y: Int, x: Int): Field =  gameBoard.board(x)(y).asInstanceOf[Field]

  def validatePlayerTargetField(y: Int, x: Int): Boolean = possibleMoves.contains((y,x))

  def validateTargetField(y: Int, x: Int):Boolean = x>=0 && y>=0 && y<gameBoard.width && x<gameBoard.height && gameBoard.board(x)(y).isFree
  
  def isWon(x: Int, y: Int): Boolean = x == 0 && y == gameBoard.width/2+1
  
  def dice(): Unit = diced = Random.nextInt(6)+1
  
  def undo(): Unit = undoManager.undoStep(this)

  def redo(): Unit = undoManager.redoStep(this)
  
  def saveGame(): Unit =
    serializer.dumps(gameBoard, gameBoard.players.indexOf(currentPlayer))

  def fromJson(js: JsValue, currentplayer: Int): ControllerInterface =
    gameBoard = gameBoard.fromJson(js)
    currentPlayer = gameBoard.players(currentplayer)
    this

  override def fromXML(node: Node, currentplayer: Int): ControllerInterface =
    gameBoard = gameBoard.fromXML(node)
    currentPlayer = gameBoard.players(currentplayer)
    this



