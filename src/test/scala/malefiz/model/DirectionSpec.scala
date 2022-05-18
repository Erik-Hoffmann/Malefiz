package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class DirectionSpec extends AnyWordSpec {
  "A Direction" should {
    "have a String representation" in {
      Direction.Up.toString should be("u")
    }
  }

}
