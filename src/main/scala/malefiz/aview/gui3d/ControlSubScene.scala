package malefiz
package aview

import gui3d.animationStates.UndoRedoState
import gui3d.Gui3d
import controller.State
import controller.ControllerInterface
import malefiz.model.Colors
import scalafx.animation.FadeTransition
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.input.KeyCode
import scalafx.scene.layout.{BorderPane, HBox, Priority, VBox}
import scalafx.scene.{Group, SceneAntialiasing, SubScene}

class ControlSubScene(con :ControllerInterface, box : HBox, viewPortSize :Int,  gui: Gui3d) extends
  SubScene(box, viewPortSize, 80, true, SceneAntialiasing.Balanced) {
  box.children = Seq()
  box.getChildren.add(new VBox{
    maxWidth = Double.MaxValue
    maxHeight = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    this.children = Seq()
    this.children.add(new Button {
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
    this.children.add(new Button{
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
  })
  box.children.add(new Button{
    text = "Yield"
    maxWidth = Double.MaxValue
    maxHeight = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    onAction = handle {
      con.state = State.MoveComplete
      con.inputExecute(0,0)
    }
  })
  box.children.add(new Button{
    text = "Yield"
    maxWidth = Double.MaxValue
    maxHeight = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    onAction = handle {
      con.state = State.Set
      con.inputExecute(0,0)
    }
  })
  box.getChildren.add(new VBox{
    maxWidth = Double.MaxValue
    maxHeight = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    this.children = Seq()
    this.children.add(new TextField {
      text = "Current Turn: Player " + mapColor(con.currentPlayer.color)
    })
    this.children.add(new TextField {
      text = "Move count: " + con.diced
    })
    this.children.add(gui.textContainer)

  })
  def mapColor(c: Colors): String ={
    c match
      case Colors.red => "Red"
      case Colors.blue => "Blue"
      case Colors.magenta => "Magenta"
      case Colors.cyan => "Cyan"
      case Colors.green => "Green"
      case Colors.yellow => "Yellow"
      case Colors.default => "Black"
  }
}
