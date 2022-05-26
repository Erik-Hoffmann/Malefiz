package malefiz.aview.gui

import scalafx.scene.control.TextField
import malefiz.aview.gui.{BoardGui, FooterButtons}
import malefiz.controller.Controller
import malefiz.model.Gameboard
import malefiz.util.Observer
import scalafx.application.JFXApp3
import scalafx.scene.layout.BorderPane
import scalafx.scene.Scene

class GUI(con : Controller) extends JFXApp3, Observer {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Malefiz"
      scene = new Scene {
        content = new BorderPane {
          center = new BoardGui(con.field)
          bottom = new FooterButtons(con)
        }
      }
    }
  }
  override def update(): Unit = start()
}
