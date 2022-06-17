
package malefiz
package controller

import controller.BaseImpl.{Controller, Turn}
import model.BaseImpl.Gameboard
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec
import util.Observer

class ControllerSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")
  "The Controller" should {

    val controller = Controller(Gameboard(1))
    "put a stone on the field when a move is made" in {
      controller.put(Turn(Option.empty, (0,0)))
      controller.field.toString should be(
      "      □       " + eol +
      " □  □  □  □  □ " + eol +
      " □           □ " + eol +
      " □  □  □  □  □ "
      )
    }
    "notify its observers on change" in {
      class TestObserver(controller: Controller) extends Observer:
        controller.add(this)
        var bing = false
        override def update(): Unit = bing = true
      val testObserver = TestObserver(controller)
      testObserver.bing should be(false)
      controller.put(Turn(Option.empty, (0,0)))
      testObserver.bing should be(true)
    }
    "undo and redo a move" in {
      controller.put(Turn(Option.empty, (1,0)))
      controller.field.toString should be(
        " \uE008     □       " + eol +
        " \uE008  □  □  □  □ " + eol +
        " □           □ " + eol +
        " □  □  □  □  □ "
      )
      controller.undo()
      controller.field.toString should be(
        " \uE008     □       " + eol +
        " □  □  □  □  □ " + eol +
        " □           □ " + eol +
        " □  □  □  □  □ "
      )
      controller.redo()
      controller.field.toString should be(
        " \uE008     □       " + eol +
          " \uE008  □  □  □  □ " + eol +
          " □           □ " + eol +
          " □  □  □  □  □ "
      )
    }
    val con = Controller(Gameboard(4))
    "change to first player" in {
      con.firstPlayer()
      con.field.currentPlayer should be(con.field.playerList(0))
    }
    con.put(Turn(Option.empty,(0,0)))
    "change to next Player" in {
      con.nextPlayer()
      con.field.currentPlayer.pegs.length should be(0)
    }
    
  }
}
