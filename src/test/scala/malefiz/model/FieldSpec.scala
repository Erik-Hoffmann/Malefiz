package malefiz
package model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldSpec extends AnyWordSpec {
  val field: Field = Field(1, 1, FreeField())
  "A Field" should {
    "have coordinates" in {
      field.getCoords should be((1,1))
    }
    "have a string representation" in {
      field.toString should be(" □ ")
    }
    "tell if it is free" in {
      field.isFree should be(true)
    }
    "tell if its a blocker" in {
      field.isBlocker should be(false)
    }
  }
}

class EmptyGroundSpec extends AnyWordSpec {
  val field: EmptyGround = EmptyGround(1,1)
  "A EmptyGround" should {
    "have a string representation" in {
      field.toString should be ("   ")
    }
    "tell id its free" in {
      field.isFree should be (false)
    }
  }
}