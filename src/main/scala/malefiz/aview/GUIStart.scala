package malefiz.aview

import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.control.TextField
import scalafx.application.Platform
import malefiz.aview.gui.GUI
import malefiz.aview.gui3d.Gui3d
import malefiz.controller.BaseImpl.Controller
import malefiz.controller.ControllerInterface
import malefiz.model.BaseImpl.Gameboard
import malefiz.util.Observer
import scalafx.application.JFXApp3
import scalafx.event.EventIncludes.handle
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.stage.Stage

class GUIStart(con : ControllerInterface) extends JFXApp3, Observer{
  con.add(this)
  var gui: Gui = new GUI(con)
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
                  gui = new GUI(con)
                  stage.scene = gui.start
                }
              },
              new Button {
                text = "3D"
                onAction = handle {
                  gui = new Gui3d(con)
                  stage.scene = gui.start
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
