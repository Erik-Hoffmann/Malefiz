package malefiz
package model.BaseImpl

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class DirectionSpec extends AnyWordSpec {
  "A Direction" should {
    "have a String representation" in {
      Direction.Up.toString should be("u")
    }
  }

}
