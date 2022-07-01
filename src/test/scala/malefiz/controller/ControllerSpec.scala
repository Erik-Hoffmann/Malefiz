package malefiz
package controller

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec {
  val myController: Controller = Controller(1)
  "A Controller" should {
    "check field if peg is on the field" in {
      myController.checkField(0,0,0) should be (false)
    }
    "get a field" in {
      myController.getTargetField(0,2).toString should be (" □ ")
    }
    "validate a possible move" in {
      myController.validatePlayerTargetField(0,2) shouldBe a [Boolean]
    }
    "validate a free field" in {
      myController.validateTargetField(0,2) should be (true)
    }
    "check if won" in {
      myController.isWon(2,0) should be (false)
    }
  }
}
