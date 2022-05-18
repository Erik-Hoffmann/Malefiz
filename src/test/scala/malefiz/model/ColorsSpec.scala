package malefiz.model

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class ColorsSpec extends AnyWordSpec {
   "A Color" should {
     "have a String reperesentation" in {
       Colors.blue.toString should be("\\u001b[34m")
     }
   }
}
