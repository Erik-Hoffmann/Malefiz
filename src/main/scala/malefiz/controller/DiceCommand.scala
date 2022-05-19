package malefiz
package controller

import model.Gameboard
import util.Command
import util.UndoManager

class DiceCommand(dice: Int) extends Command[Gameboard]:
  override def noStep(field: Gameboard): Gameboard = field
  override def doStep(field: Gameboard): Gameboard = field
  override def undoStep(field: Gameboard): Gameboard = field
  override def redoStep(field: Gameboard): Gameboard = field
