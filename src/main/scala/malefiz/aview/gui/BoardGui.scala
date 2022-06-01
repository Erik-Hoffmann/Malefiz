package malefiz.aview.gui

import malefiz.controller.BaseImpl.{Controller, Turn}
import malefiz.controller.ControllerInterface
import malefiz.model.BaseImpl.{Blocker, Empty, FreeField, Gameboard, Peg}
import scalafx.event.EventIncludes.handle
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.{Circle, Rectangle}

class BoardGui(con : ControllerInterface) extends GridPane {
  val container = new GridPane()
  for (i <- 0 until con.field.height) {
    for (j <- 0 until con.field.width) {
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
      con.put(new Turn(Option.empty, position))
    }
  }
}
