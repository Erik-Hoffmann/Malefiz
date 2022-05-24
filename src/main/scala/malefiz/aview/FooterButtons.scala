package malefiz.aview

import scalafx.event.EventIncludes.handle
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, Priority}

class FooterButtons extends HBox {
  val undoButton = new UndoButton
  val redoButton = new RedoButton
  val navigationButton = new NavigationButtons
  children = Seq(undoButton, redoButton, navigationButton)
}
class UndoButton extends Button {
  text = "Undo"
  maxWidth = Double.MaxValue
  hgrow = Priority.Always
  onAction = handle {
    //TODO link with undo in controller
  }
}
class RedoButton extends Button {
  text = "Redo"
  maxWidth = Double.MaxValue
  hgrow = Priority.Always
  onAction = handle {
    //TODO link with redo in controller
  }
}
