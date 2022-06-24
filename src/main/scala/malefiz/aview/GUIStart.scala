package malefiz
package aview

import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.control.TextField
import scalafx.application.Platform
import aview.gui3d.Gui3d
import controller.BaseImpl.Controller
import controller.ControllerInterface
import model.BaseImpl.Gameboard
import util.Observer
import scalafx.application.JFXApp3
import scalafx.event.EventIncludes.handle
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.stage.Stage

class GUIStart(con : ControllerInterface) extends JFXApp3, Observer{
  con.add(this)
  var gui: Gui = new Gui:
    override def update: Scene = {return new Scene()}
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Malefiz"
      scene = new Scene {
        content = new VBox {
          children = Seq(new TextField {
            text = "Welche Gui?"
          },
          new HBox{
            this.children = Seq(
              new Button{
                text = "2D"
                onAction = handle {
                  stage.scene = gui.update
                }
              },
              new Button {
                text = "3D"
                onAction = handle {
                  gui = new Gui3d(con)
                  stage.scene = gui.update
                }
              }
            )
          })
        }
      }
    }
  }
  override def update(): Unit = {
    Platform.runLater {
      stage.scene = gui.update
    }
  }
}
