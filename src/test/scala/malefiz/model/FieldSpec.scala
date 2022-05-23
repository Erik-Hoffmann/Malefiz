package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  "A Field" should {
    val field = Field(1, 1, Stone(Fields.FreeField))
    "have a String representation" in {
      field.toString should be(" □ ")
    }
    "tell if it is free" in {
      field.isFree should be(true)
    }
  }

}
