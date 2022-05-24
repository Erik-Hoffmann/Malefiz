package malefiz.aview

import malefiz.aview.GUI.stage
import malefiz.model.Gameboard
import scalafx.application.JFXApp3
import scalafx.beans.binding.Bindings.when
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.*
import scalafx.scene.paint.Color.{Black, Blue, DarkGray, DarkRed, Gray, Red, White}
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.scene.text.Text
import scalafx.scene.{Group, Node, Scene}
import scalafx.stage.Stage

val board: Array[List[Int]] = Array(
  List(0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0),
  List(1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1),
  List(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1),
  List(1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1),
  List(0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0),
  List(0,0,0,0,0,0,1,1,2,1,1,0,0,0,0,0,0),
  List(0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0),
  List(0,0,0,0,1,1,2,1,1,1,2,1,1,0,0,0,0),
  List(0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0),
  List(0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0),
  List(0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0),
  List(2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,1,2),
  List(1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1),
  List(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
);

object GUI extends JFXApp3 {
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "Malefiz"
      scene = new Scene {
        content = new BorderPane {
          center = new BoardGui(board)
          bottom = new FooterButtons
        }
      }
    }
  }
}
