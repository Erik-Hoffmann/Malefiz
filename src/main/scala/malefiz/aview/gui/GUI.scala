package malefiz.aview.gui

import malefiz.aview.Gui
import scalafx.scene.control.TextField
import malefiz.aview.gui.{BoardGui, FooterButtons}
import malefiz.controller.BaseImpl.Controller
import malefiz.controller.ControllerInterface
import malefiz.model.BaseImpl.Gameboard
import malefiz.util.Observer
import scalafx.scene.layout.BorderPane
import scalafx.scene.Scene
import scalafx.application.{JFXApp3, Platform}
import scalafx.stage.Stage

class GUI(con : ControllerInterface) extends Gui {
  override def update: Scene = {
    new Scene {
      content = new BorderPane {
        center = new BoardGui(con)
        bottom = new FooterButtons(con)
      }
    }
  }
}
