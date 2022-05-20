package malefiz.util

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

//class incrCommand extends Command[Int]:
//  override def doStep(): Unit = state + 1
//  override def undoStep(): Unit = state - 1
//  override def redoStep(): Unit = state + 1

//class CommandSpec extends AnyWordSpec {
//  "A Command" should {
//    val command = new incrCommand
//    "have a do step" in {
//      command.doStep(0) should be(1)
//      command.doStep(1) should be(2)
//    }
//    "have an undo step" in {
//      command.undoStep(1) should be(0)
//    }
//    "have a redo step" in {
//      command.redoStep(1) should be(2)
//    }
//  }
//}