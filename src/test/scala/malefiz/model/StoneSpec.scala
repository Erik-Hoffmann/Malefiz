package malefiz
package model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class StoneSpec extends AnyWordSpec {
  val stone: Stone = Stone("hehe")
  "A Stone" should {
    "have a string representation" in {
      stone.toString should be ("hehe")
    }
  }
}

class PegSpec extends AnyWordSpec {
  val peg: Peg = Peg(Colors.cyan)
  "A Peg" should {
    "have a string representation" in {
      peg.toString should be ("\u001B[36m \uE008 \u001B[0m")
    }
  }
}

class FreeFieldSpec extends AnyWordSpec {
  val freeField: FreeField = FreeField()
  "A FreeField" should {
    "have a string representation" in {
      freeField.toString should be (" □ ")
    }
  }
}

class BlockerSpec extends AnyWordSpec {
  val blocker: Blocker = Blocker()
  "A Blocker" should {
    "have a string representation" in {
      blocker.toString should be (" ■ ")
    }
  }
}

class EmptySpec extends AnyWordSpec {
  val empty: Empty = Empty()
  "A Empty" should {
    "have a string representation" in {
      empty.toString should be ("   ")
    }
  }
}
