package malefiz.util

trait Command[T]:
  def doStep(): Unit
  def undoStep(): Unit
  def redoStep(): Unit

class UndoManager[T]:

  private var undoStack: List[Command[T]] = Nil
  private var redoStack: List[Command[T]] = Nil

  def doStep(command: Command[T]): Unit =
    undoStack = command :: undoStack
    command.doStep()

  def undoStep(): Unit =
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoStep()
        undoStack = stack
        redoStack = head :: redoStack
      }
    }

  def redoStep(): Unit =
    redoStack match {
      case Nil =>
      case head :: stack => {
        head.redoStep()
        redoStack = stack
        undoStack = head :: undoStack
      }
    }