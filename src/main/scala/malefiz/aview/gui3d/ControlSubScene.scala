package malefiz.aview.gui3d

import malefiz.aview.gui3d.animationStates.UndoRedoState
import malefiz.controller.ControllerInterface
import scalafx.animation.FadeTransition
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.Button
import scalafx.scene.input.KeyCode
import scalafx.scene.layout.{BorderPane, HBox, Priority}
import scalafx.scene.{Group, SceneAntialiasing, SubScene}

class ControlSubScene(con :ControllerInterface, box : HBox, viewPortSize :Int,  gui: Gui3d) extends
  SubScene(box, viewPortSize, 80, true, SceneAntialiasing.Balanced) {
  box.children = Seq()
  box.children.add(new Button {
    text = "Undo"
    maxWidth = Double.MaxValue
    maxHeight = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    onAction = handle {
      gui.guiState = new UndoRedoState()
      gui.guiState.playBefore(gui)
      con.undo()
    }
  })
  box.children.add(new Button{
    text = "Redo"
    maxWidth = Double.MaxValue
    maxHeight = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    onAction = handle {
      gui.guiState = new UndoRedoState()
      gui.guiState.playBefore(gui)
      con.redo()
    }
  })
  box.children.add(new BorderPane{
    hgrow = Priority.Always
    vgrow = Priority.Always
    maxHeight = Double.MaxValue
    top = new Button {
      text = "Up"
      maxWidth = Double.MaxValue
      onAction = handle {

      }
    }
    center = new HBox {
      this.hgrow = Priority.Always
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
      maxHeight = Double.MaxValue
      hgrow = Priority.Always
      vgrow = Priority.Always
      onAction = handle {

      }
    }
  })

}
