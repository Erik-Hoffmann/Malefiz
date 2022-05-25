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
      if (board.getField(i,j).isInstanceOf[Empty]) {
        container.add(new Rectangle {
          height = 40
          width = 40
          fill = Transparent
        }, j, i)
      } else if(board.getField(i,j).isInstanceOf[FreeField]){
        container.add(new FieldStone(Blue, (i,j)), j, i)
      } else if(board.getField(i,j).isInstanceOf[Blocker]){
        container.add(new FieldStone(Black, (i,j)), j, i)
      } else if(board.getField(i,j).isInstanceOf[Peg]) {
        container.add(new FieldStone(Red, (i,j)), j, i)
      }
    }
    children = container
  }
  class FieldStone(color: Color, position: (Int, Int)) extends Circle {
    radius = 20
    fill = color
    onMouseClicked = handle {
      //TODO give back position and proceed like tui
    }
  }
}
