package malefiz.aview.gui

import javafx.scene.input.KeyCode
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.Button
import scalafx.scene.layout.{BorderPane, HBox, Priority}

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
  center = new HBox {
    this.hgrow = Priority.Always
    this.children = Seq()
    this.children.add(new Button {
      text = "Left"
      this.maxWidth = Double.MaxValue
      this.maxHeight = Double.MaxValue
      this.hgrow = Priority.Always
      this.vgrow = Priority.Always
      onAction = handle {

      }
    })
    this.children.add(new Button {
      text = "Right"
      this.maxWidth = Double.MaxValue
      this.maxHeight = Double.MaxValue
      this.hgrow = Priority.Always
      this.vgrow = Priority.Always
      onAction = handle {

      }
    })
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


