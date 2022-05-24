package malefiz.aview

import malefiz.model.Gameboard
import scalafx.event.EventIncludes.handle
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.{Circle, Rectangle}

class BoardGui(board: Array[List[Int]]) extends GridPane {
  val container = new GridPane()
  for (i <- 0 until board.size) {
    for (j <- 0 until board(i).size) {
      if (board(i)(j) == 0) {
        container.add(new Rectangle {
          height = 40
          width = 40
          fill = Transparent
        }, j, i)
      } else if(board(i)(j) == 1){
        container.add(new FieldStone(Blue, (i,j)), j, i)
      } else if(board(i)(j) == 2){
        container.add(new FieldStone(Black, (i,j)), j, i)
      } else if(board(i)(j) == 3) {
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
