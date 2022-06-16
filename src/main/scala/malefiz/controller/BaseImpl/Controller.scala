package malefiz.controller.BaseImpl

import State.Output
import malefiz.controller.ControllerInterface
import malefiz.model.GameboardInterface
import malefiz.util.UndoManager

case class Controller(board: GameboardInterface) extends ControllerInterface(board) :
  val undoManager = new UndoManager[GameboardInterface]
  var state: State = Output
  dice()
  field.buildBoard()

  def nextPlayer(): Unit = {
    if (field.playerList.indexOf(field.currentPlayer) + 1 == field.playerList.length) {
      firstPlayer();
    }else {
      field.currentPlayer = field.playerList(field.playerList.indexOf(field.currentPlayer) + 1)
    }
  }

  def firstPlayer(): Unit =
    field.currentPlayer = field.playerList(0)

  def legalMove (turn: Turn): Boolean = {
    return !board.legalMove(turn.srcPos.get, turn.destPos, diced);
  }

  def movePeg(turn: Turn): Unit = {
    diced = diced -1;
    field.currentPlayer.pegs.remove(field.currentPlayer.pegs.indexOf(turn.srcPos.get))
    field.currentPlayer.pegs :+= turn.destPos;
    if(diced == 0){
      if(board.checkBlocker(turn.destPos)){
        undoManager.doStep(field, ExecuteTurnCommand(turn, this));
        board.removeBlocker(turn.srcPos.get)
      }else if (board.checkPeg(turn.destPos)){
        undoManager.doStep(field, ExecuteTurnCommand(turn, this));
        board.sendPegHome(turn.srcPos.get)
      } else  {
        undoManager.doStep(field, ExecuteTurnCommand(turn, this));
        nextPlayer();
        dice();
      }
    } else {
      undoManager.doStep(field, ExecuteTurnCommand(turn, this));
    }
    notifyObservers()
  }
  
  def put(turn: Turn): Unit =
    undoManager.doStep(field, ExecuteTurnCommand(turn, this))
    notifyObservers()

  def undo(): Unit =
    field = undoManager.undoStep(field)
    notifyObservers()


  def redo(): Unit =
    undoManager.redoStep(field)
    notifyObservers()


  override def toString: String = field.toString

  def dice(): Unit = diced = scala.util.Random.nextInt(6) + 1
