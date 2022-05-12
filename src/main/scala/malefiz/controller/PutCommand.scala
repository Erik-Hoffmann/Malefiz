package malefiz
package controller

import model.Field
import model.Move
import model.Token
import util.Command
import util.UndoManager

class PutCommand(move: Move) extends Command[Field]:
  override def noStep(field: Field): Field = field
  override def doStep(field: Field): Field = field.put(move.token, move.x, move.y)
  override def undoStep(field: Field): Field = field.put(Token("space"), move.x, move.y)
  override def redoStep(field: Field): Field = field.put(move.token, move.x, move.y)