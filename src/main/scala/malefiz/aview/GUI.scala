package malefiz.aview

import malefiz.aview.GUI.stage
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
          val container = new GridPane()
          for (i <- 0 until board.size) {
            for (j <- 0 until board(i).size) {
              if (board(i)(j) == 0) {
                container.add(new Rectangle {
                  height = 40
                  width = 40
                  fill = White
                }, j, i)
              } else if(board(i)(j) == 1){
                container.add(new Circle {
                  radius = 20
                  fill = Blue
                }, j, i)
              } else if(board(i)(j) == 2){
                container.add(new Circle {
                  radius = 20
                  fill = Black
                }, j, i)
              } else if(board(i)(j) == 3) {
                container.add(new Circle {
                  radius = 20
                  fill = Red
                }, j, i)
              }
            }
          }
          center = container
          val box = new HBox()
          val undoButton = new Button {
            text = "undo"
            maxWidth = Double.MaxValue
          }
          val redoButton = new Button {
            text = "redo"
            maxWidth = Double.MaxValue
          }

          val navigationButton = new BorderPane{
            this.top = new Button {
              text = "Up"
              maxWidth = Double.MaxValue
            }
            this.bottom = new Button {
              text = "Down"
              maxWidth = Double.MaxValue
            }
            val rightButton= new Button {
              text = "Right"
              maxWidth = Double.MaxValue
            }
            val leftButton = new Button {
              text = "Left"
              maxWidth = Double.MaxValue
            }
            HBox.setHgrow(rightButton, Priority.Always)
            HBox.setHgrow(leftButton, Priority.Always)
            this.right = rightButton
            this.left = leftButton
          }
          HBox.setHgrow(undoButton , Priority.ALWAYS)
          HBox.setHgrow(redoButton, Priority.Always)
          HBox.setHgrow(navigationButton,Priority.Always)
          box.getChildren.addAll(undoButton, redoButton, navigationButton)
          bottom = box
        }
      }
    }
  }
}
