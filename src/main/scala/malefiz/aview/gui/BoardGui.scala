package malefiz.aview.gui

import malefiz.model.{Blocker, Empty, FreeField, Gameboard, Peg}
import scalafx.event.EventIncludes.handle
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.{Circle, Rectangle}

class BoardGui(board: Gameboard) extends GridPane {
  val container = new GridPane()
  for (i <- 0 until board.board.size) {
    for (j <- 0 until board.board(i).size) {
      if (board.checkEmpty(i,j)) {
        container.add(new Rectangle {
          height = 40
          width = 40
          fill = Transparent
        }, j, i)
      } else if(board.checkFreeField(i,j)){
        container.add(new FieldStone(Blue, (i,j)), j, i)
      } else if(board.checkBlocker(i,j)){
        container.add(new FieldStone(Black, (i,j)), j, i)
      } else if(board.checkPeg(i,j)) {
        container.add(new FieldStone(Red, (i,j)), j, i)
      }
    }
    children = container
  }
  class FieldStone(color: Color, position: (Int, Int)) extends Circle {
    radius = 20
    fill = color
  }
}
