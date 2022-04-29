package malefiz
package controller

import model.Field
import model.Tokens
import model.Matrix
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import util.Observer

class ControllerSpec extends AnyWordSpec {
 "Controller" should {
   val controller = Controller(new Field(5, Tokens.field))
 }
}
