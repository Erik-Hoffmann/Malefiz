package malefiz.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SpaceSpec extends AnyWordSpec with Matchers{
  "A Space" when {
    "set to a specific value" should {
      val nonEmptySpace = new Space(1)
      "have a value" in {
        nonEmptySpace.field should be (1)
      }
      "have a String representation" in {
        nonEmptySpace.toString should be (" □ ")
      }
    }
  }
}
