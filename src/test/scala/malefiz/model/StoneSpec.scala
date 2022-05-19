package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec {
  "A Stone" should {
    val stone = Stone("empty")
    "have a freefield with a String representation" in {
      stone.toString should be("   ")
    }
    val blocker = Stone("blocker")
    "have a blocker with a String representation" in {
      blocker.toString should be(" ■ ")
    }
    val freefield = Stone("freefield")
    "have a freefield with a string representation" in {
      freefield.toString should be (" □ ")
    }
    val peg = Stone("peg")
    "have a peg with a String representation" in {
      peg.toString should be(" \uE008 ")
    }
  }
}
