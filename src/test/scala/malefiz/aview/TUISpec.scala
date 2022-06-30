package malefiz
package aview

import controller.Controller
import model.Field
import model.Tokens
import model.Matrix
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec {
 "TUI" should {
   val tui = TUI(Controller(new Field(5, Tokens.field)))
 }
}
