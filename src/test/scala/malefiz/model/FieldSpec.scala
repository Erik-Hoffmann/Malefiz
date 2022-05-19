package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  "A Field" should {
    val field = new Field(1, 1, Stone("freefield"))
    "have a String representation" in {
      field.toString should be(" □ ")
    }
    "tell if it is free" in {
      field.isFree should be(true)
    }
  }

}
