package malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec{
  "A Player" when { "new" should {
    val player = Player(Colors.red)
    "have an Array with pegs" in {
//      player.stoneObs should be(Array[Peg]())
    }
  }
    
  }
}
