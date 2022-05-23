package malefiz.util

trait Command[T]:
  def noStep(state: T): T
  def doStep(state: T): T
  def undoStep(state: T): T
  def redoStep(state: T): T

class UndoManager[T]:

  private var undoStack: List[Command[T]] = Nil
  private var redoStack: List[Command[T]] = Nil

  def doStep(state: T, command: Command[T]): T =
    undoStack = command :: undoStack
    command.doStep(state)

  def undoStep(state: T): T =
    undoStack match {
      case Nil => state
      case head :: stack =>
        val result = head.undoStep(state)
        undoStack = stack
        redoStack = head :: redoStack
        result
    }

  def redoStep(state: T): T =
    redoStack match {
      case Nil => state
      case head :: stack =>
        val result = head.redoStep(state)
        redoStack = stack
        undoStack = head :: undoStack
        result
    }