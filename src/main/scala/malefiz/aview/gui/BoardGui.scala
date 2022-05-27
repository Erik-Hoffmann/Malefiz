package malefiz.aview.gui

import malefiz.controller.Controller
import malefiz.model.{Blocker, Empty, FreeField, Gameboard, Peg, Turn}
import scalafx.event.EventIncludes.handle
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.{Circle, Rectangle}

class BoardGui(con : Controller) extends GridPane {
  val container = new GridPane()
  for (i <- 0 until con.field.board.size) {
    for (j <- 0 until con.field.board(i).size) {
      if (con.field.checkEmpty(i,j)) {
        container.add(new Rectangle {
          height = 40
          width = 40
          fill = Transparent
        }, j, i)
      } else if(con.field.checkFreeField(i,j)){
        container.add(new FieldStone(Blue, (i,j)), j, i)
      } else if(con.field.checkBlocker(i,j)){
        container.add(new FieldStone(Black, (i,j)), j, i)
      } else if(con.field.checkPeg(i,j)) {
        container.add(new FieldStone(Red, (i,j)), j, i)
      }
    }
    children = container
  }
  class FieldStone(color: Color, position: (Int, Int)) extends Circle {
    radius = 20
    fill = color
    onMouseClicked = handle{
      println(position)
      con.put(new Turn(Option.empty, position))
    }
  }
}
