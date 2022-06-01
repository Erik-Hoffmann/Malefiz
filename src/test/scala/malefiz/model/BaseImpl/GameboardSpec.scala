package malefiz.model.BaseImpl

import malefiz.model.BaseImpl.{Gameboard, Stone}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class GameboardSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")
  "The Gameboard" should {
    val theBoard = Gameboard(1)
    "have dimensions" in {
      theBoard.width should be (5)
      theBoard.height should be (4)
    }
    "return array with spaced ground items" in {
      theBoard.updateRowFilled(0, 1).map(i => i.toString).mkString("") should be  ("       □       ")
      theBoard.updateRowFilled(0, 5).map(i => i.toString).mkString("") should be  (" □  □  □  □  □ ")
      theBoard.updateRowSpaced(0, 2).map(i => i.toString).mkString("") should be    (" □           □ ")
    }
    "return an array of players" in {
      theBoard.createPlayers.length should be (1)
      theBoard.playerList.length should be (1)
    }
    "return an fully initialized gameboard" in {
      theBoard.buildBoard().toString should be (
        "       □       " + eol +
        " □  □  □  □  □ " + eol +
        " □           □ " + eol +
        " □  □  □  □  □ "
      )
    }

    "return an example updated gameboard" in {
      theBoard.exampleUpdateBoard().toString should be (
        " □  □  □  □  □ " + eol +
          " □  □  □  □  □ " + eol +
          " □           □ " + eol +
          " □  □  □  □  □ "
      )
    }

    "return an updated gameboard" in {
      theBoard.put(Stone.apply(Fields.Peg), 0,0).toString should be (
        " \uE008  □  □  □  □ " + eol +
          " □  □  □  □  □ " + eol +
          " □           □ " + eol +
          " □  □  □  □  □ "
      )
    }
    "check if there is a peg" in {
      theBoard.checkPeg(0,0 )should be(true)
    }
    "check if there is a FreeField" in {
      theBoard.checkFreeField((1,1)) should be(true)
    }
    "check if there is an empty field" in {
      theBoard.checkEmpty(2,1) should be(true)
    }
    var newBoard = theBoard.buildBoard()
    "check if there is a Blocker" in {
      newBoard = newBoard.put(Stone.apply(Fields.Blocker),0,0)
      newBoard.checkBlocker(0,0) should be(true)
    }
    "move the blocker" in {
      newBoard.switchPos(Option.apply((0,0)),(1,1))
      newBoard.checkBlocker(1,1) should be(true)
    }
  }
}
