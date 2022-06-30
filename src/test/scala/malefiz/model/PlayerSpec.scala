package malefiz
package model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.matchers.BeMatcher.*
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec{
  val player: Player = Player(Colors.cyan, (2,3))
  val pegField: Field = Field(0,0,Peg(Colors.cyan))
  val targetField: Field = Field(1,0, FreeField())
  player.pegs.update(0, pegField)
  "A Player" should {
    "have a number of pegs" in {
      player.numPegs should be (5)
    }
    "have no pegs by default" in {
      player.pegs shouldBe an [Array[Field]]
      player.pegs.filterNot(_==null).length should be (1)
    }
    "update pegs" in {
      player.updatePeg(pegField, targetField)
      player.pegs(0).x should be (1)
    }
    "have one peg" in {
      player.getPegs.length should be (1)
    }
    "get pegs by position" in {
      player.getPegByPos(1,0) shouldBe an [Option[Field]]
      player.getPegByPos(1,1) should be (None)
    }
    "have a string representation" in {
      player.toString should be ("\u001B[36m\uE008\u001B[0m")
    }
    "remove a peg" in {
      player.removePeg(1,0)
      player.pegs.filterNot(_==null).length should be (0)
    }
    "have a pegs json" in {
      player.pegsToJSON.toString should be ("[]")
    }
    "have a json representation" in {
      player.toJson.toString should be ("{\"color\":\"\\u001B[36m\",\"startField\":[2,3],\"pegs\":[]}")
    }
  }
}
