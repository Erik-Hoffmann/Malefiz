package malefiz
package model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

case class myGround() extends Ground {
  override def toString: String = "hehe"
}

class GroundSpec extends AnyWordSpec {
  val myGroundInstance: myGround = myGround()
  "Ground" should {
    "be free by default" in {
      myGroundInstance.isFree should be (true)
    }
    "have a string representation" in {
      myGroundInstance.toString should be ("hehe")
    }
  }
}
