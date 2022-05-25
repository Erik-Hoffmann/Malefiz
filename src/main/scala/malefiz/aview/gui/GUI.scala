package malefiz.aview.gui

import malefiz.aview.gui.{BoardGui, FooterButtons}
import malefiz.controller.Controller
import malefiz.model.Gameboard

import scalafx.application.JFXApp3
import scalafx.scene.layout.BorderPane
import scalafx.scene.Scene

class GUI extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Malefiz"
      scene = new Scene {
        content = new BorderPane {
          center = new BoardGui(GUI.con(0).field)
          bottom = new FooterButtons
        }
      }
    }
  }
}
object GUI {
  var con = List(Controller)
  def init(con: Controller): Unit = {
    this.con :+ con
    main()
  }
}
