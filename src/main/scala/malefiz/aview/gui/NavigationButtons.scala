package malefiz.aview.gui

import javafx.scene.input.KeyCode
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.Button
import scalafx.scene.layout.{BorderPane, Priority}

class NavigationButtons extends BorderPane {
  hgrow = Priority.Always
  def upPressed(): Unit = {}
  def leftPressed(): Unit = {}
  def rightPressed(): Unit = {}
  def downPressed(): Unit = {}
  top = new Button {
    text = "Up"
    maxWidth = Double.MaxValue
    onAction = handle {
      upPressed()
    }
  }
  left = new Button {
    text = "Left"
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
    onAction = handle {
      leftPressed()
    }
  }
  right = new Button {
    text = "Right"
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
    onAction = handle {
      rightPressed()
    }
  }
  bottom = new Button {
    text = "Down"
    maxWidth = Double.MaxValue
    onAction = handle {
      downPressed()
    }
  }
  onKeyPressed  = { handle =>
    handle.getCode match {
      case KeyCode.UP => upPressed()
      case KeyCode.LEFT => leftPressed()
      case KeyCode.RIGHT => rightPressed()
      case KeyCode.DOWN => downPressed()
    }
  }

}


