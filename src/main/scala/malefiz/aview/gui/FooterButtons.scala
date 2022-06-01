package malefiz.aview.gui

import malefiz.controller.ControllerInterface
import scalafx.scene.control.Label
import scalafx.scene.control.TextField
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, Priority}

class FooterButtons(con : ControllerInterface) extends HBox {
  val undoButton = new UndoButton
  val redoButton = new RedoButton
  val textField = new Label {
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
  }
  val navigationButton = new NavigationButtons
  children = Seq(undoButton, redoButton, textField, navigationButton)

  class UndoButton extends Button {
    text = "Undo"
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
    onAction = handle {
      con.undo
    }
  }
  class RedoButton extends Button {
    text = "Redo"
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
    onAction = handle {
      con.redo()
    }
  }
}
