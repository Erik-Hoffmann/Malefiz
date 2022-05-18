package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec {
  "A Stone" should {
    val stone = Stone.getStone("empty")
    "have a freefield with a String representation" in {
      stone.toString should be("   ")
    }
    val blocker = Stone.getStone("blocker")
    "have a blocker with a String representation" in {
      blocker.toString should be(" ■ ")
    }
    val field = new Field(1, 1, stone)
    val peg = Stone.getStone(field)
    "have a peg with a String representation" in {
      peg.toString should be(" \uE008 ")
    }
    "have a position" in {
      peg.position.toString should be("   ")
    }
  }
}
