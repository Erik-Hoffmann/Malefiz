package malefiz
package aview

import gui3d.animationStates.UndoRedoState
import gui3d.Gui3d
import controller.ControllerInterface
import scalafx.animation.FadeTransition
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.input.KeyCode
import scalafx.scene.layout.{BorderPane, HBox, Priority, VBox}
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
  box.getChildren.add(new VBox{
    this.children = Seq()
    this.children.add(new TextField {
      text = "Current Turn: Player " + con.currentPlayer.toString
    })
    this.children.add(new TextField {
      text = "Move count: " + con.diced
    })
    this.children.add(gui.textContainer)

  })
  box.children.add(new BorderPane{
    hgrow = Priority.Always
    vgrow = Priority.Always
    maxHeight = Double.MaxValue
    top = new Button {
      text = "Up"
      maxWidth = Double.MaxValue
      onAction = handle {
        /*
        if (!gui.choosenPeg.isEmpty) {
          val src = (gui.choosenPeg.get.posx, gui.choosenPeg.get.posy)
          val dest = (gui.choosenPeg.get.posx-1, gui.choosenPeg.get.posy)
          val turn = Turn(Option.apply(src), dest)
          if(!con.legalMove(turn)) {
            gui.choosenPeg.get.posx = gui.choosenPeg.get.posx -1
            if(con.diced == 1) {
              gui.moved = false
              gui.choosenPeg = None
            }
            con.movePeg(Turn(Option.apply(src), dest))
          }else {
            gui.textContainer.text = "Illegal Move";
          }
        }
        */
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
          /*
          if (!gui.choosenPeg.isEmpty) {
            val src = (gui.choosenPeg.get.posx, gui.choosenPeg.get.posy)
            val dest = (gui.choosenPeg.get.posx, gui.choosenPeg.get.posy-1)
            val turn = Turn(Option.apply(src), dest)
            if(!con.legalMove(turn)) {
              gui.choosenPeg.get.posy = gui.choosenPeg.get.posy -1
              if(con.diced == 1) {
                gui.moved = false
                gui.choosenPeg = None
              }
              con.movePeg(Turn(Option.apply(src), dest))
            }else {
              gui.textContainer.text = "Illegal Move";
            }
          }
          */
        }
      })
      this.children.add(new Button {
        text = "Right"
        this.maxWidth = Double.MaxValue
        this.maxHeight = Double.MaxValue
        this.hgrow = Priority.Always
        this.vgrow = Priority.Always
        onAction = handle {
          /*
          if (!gui.choosenPeg.isEmpty) {
            val src = (gui.choosenPeg.get.posx, gui.choosenPeg.get.posy)
            val dest = (gui.choosenPeg.get.posx, gui.choosenPeg.get.posy +1)
            val turn = Turn(Option.apply(src), dest)
            if(!con.legalMove(turn)) {
              gui.choosenPeg.get.posy = gui.choosenPeg.get.posy +1
              if(con.diced == 1) {
                gui.moved = false
                gui.choosenPeg = None
              }
              con.movePeg(Turn(Option.apply(src), dest))
            }else {
              gui.textContainer.text = "Illegal Move";
            }
          }
          */
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
        /*
        if (!gui.choosenPeg.isEmpty) {
          val src = (gui.choosenPeg.get.posx, gui.choosenPeg.get.posy)
          val dest = (gui.choosenPeg.get.posx +1, gui.choosenPeg.get.posy)
          val turn = Turn(Option.apply(src), dest)
          if(!con.legalMove(turn)) {
            gui.choosenPeg.get.posx = gui.choosenPeg.get.posx +1
            if(con.diced == 1) {
              gui.moved = false
              gui.choosenPeg = None
            }
            con.movePeg(Turn(Option.apply(src), dest))
          }else {
            gui.textContainer.text = "Illegal Move";
          }
        }
        */
      }
    }
  })
}
