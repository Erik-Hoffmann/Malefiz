package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class MoveSpec extends AnyWordSpec {
  "A Move" should {
    val move = new Move(Stone("freefield"), 1, 1)
    "have parameters" in {
      move.stone.toString should be(" □ ")
      move.x should be(1)
      move.y should be(1)
    }
  }
}
