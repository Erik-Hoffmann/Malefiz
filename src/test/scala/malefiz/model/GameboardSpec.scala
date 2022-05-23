package malefiz.model

import org.scalatest.matchers.should.Matchers._
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
      theBoard.put(Stone(Fields.Peg), 0,0).toString should be (
        " \uE008  □  □  □  □ " + eol +
          " □  □  □  □  □ " + eol +
          " □           □ " + eol +
          " □  □  □  □  □ "
      )
    }
  }
}
