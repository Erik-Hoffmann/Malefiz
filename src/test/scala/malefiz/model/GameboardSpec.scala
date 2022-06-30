package malefiz
package model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.matchers.BeMatcher.*
import org.scalatest.wordspec.AnyWordSpec

class GameboardSpec extends AnyWordSpec {
  val eol: String = sys.props("line.separator")
  val theBoard: GameBoard = GameBoard(1)
  "The GameBoard" should {
    "have dimensions" in {
      theBoard.width should be (5)
      theBoard.height should be (4)
    }
    "have an array with Grounds" in {
      theBoard.board shouldBe a [Array[Array[Ground]]]
    }
    "have an array with Players" in {
      theBoard.players shouldBe a [Array[Player]]
    }

    "have a builded interface" in {
      theBoard.buildGame shouldBe a [GameBoardInterface]
    }
    "have an array with placed Pegs" in {
      theBoard.board shouldBe a [Array[Array[Ground]]]
      theBoard.board(3)(2).toString should be ("[31m  [0m")
    }
    "have a string representation" in {
      theBoard.toString should be (
        s"    0  1  2  3  4 $eol"+
        s" 0        □       $eol"+
        s" 1  □  □  □  □  □ $eol"+
        s" 2  □           □ $eol"+
        s" 3  □  □ [31m  [0m □  □ "
      )
    }
    "have a json dump" in {
      theBoard.toJson.toString should be ("")
    }
    "load a json dump" in {
      theBoard.fromJson should be ("")
    }
  }
}
