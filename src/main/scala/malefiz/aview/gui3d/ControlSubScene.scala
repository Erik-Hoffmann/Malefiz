package malefiz.aview.gui3d

import malefiz.controller.ControllerInterface
import scalafx.animation.FadeTransition
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.Button
import scalafx.scene.input.KeyCode
import scalafx.scene.layout.{BorderPane, HBox, Priority}
import scalafx.scene.{Group, SceneAntialiasing, SubScene}

class ControlSubScene(con :ControllerInterface, box : HBox, viewPortSize :Int, transition: FadeTransition, state :StateClass) extends
  SubScene(box, viewPortSize, 80, true, SceneAntialiasing.Balanced) {
  box.children = Seq()
  box.children.add(new Button {
    text = "Undo"
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    onAction = handle {
      state.state = State.undo
      transition.play()
      con.undo

    }
  })
  box.children.add(new Button{
    text = "Redo"
    maxWidth = Double.MaxValue
    hgrow = Priority.Always
    vgrow = Priority.Always
    onAction = handle {
      state.state = State.redo
      transition.play()
      con.redo()
    }
  })
  box.children.add(new BorderPane{
    hgrow = Priority.Always
    vgrow = Priority.Always
    top = new Button {
      text = "Up"
      maxWidth = Double.MaxValue
      onAction = handle {

      }
    }
    left = new Button {
      text = "Left"
      maxWidth = Double.MaxValue
      hgrow = Priority.Always
      vgrow = Priority.Always
      onAction = handle {

      }
    }
    right = new Button {
      text = "Right"
      maxWidth = Double.MaxValue
      hgrow = Priority.Always
      vgrow = Priority.Always
      onAction = handle {

      }
    }
    bottom = new Button {
      text = "Down"
      maxWidth = Double.MaxValue
      hgrow = Priority.Always
      vgrow = Priority.Always
      onAction = handle {

      }
    }
  })

}
