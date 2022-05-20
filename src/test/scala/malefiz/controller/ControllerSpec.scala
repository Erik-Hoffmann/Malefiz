//package malefiz
//package controller
//
//import model.Gameboard
//import model.Move
//import model.Stone
//import org.scalatest.matchers.should.Matchers._
//import org.scalatest.wordspec.AnyWordSpec
//import util.Observer
//
//class ControllerSpec extends AnyWordSpec {
//  val eol: String = sys.props("line.separator")
//  "The Controller" should {
//    val controller = Controller(Gameboard(1).buildBoard())
//    "put a stone on the field when a move is made" in {
//      val fieldWithMove = controller.put(Move(Stone("peg"), 0, 0))
//      fieldWithMove.toString should be(
//      "      □       " + eol +
//      " □  □  □  □  □ " + eol +
//      " □           □ " + eol +
//      " □  □  □  □  □ "
//      )
//    }
//    "notify its observers on change" in {
//      class TestObserver(controller: Controller) extends Observer:
//        controller.add(this)
//        var bing = false
//        def update = bing = true
//      val testObserver = TestObserver(controller)
//      testObserver.bing should be(false)
//      controller.doAndPublish(controller.put, 0)
//      testObserver.bing should be(true)
//    }
//    "undo and redo a move" in {
//      var field = controller.field
//      controller.save
//      field.toString should be (
//        (
//          " \uE008     □       " + eol +
//          " □  □  □  □  □ " + eol +
//          " □           □ " + eol +
//          " □  □  □  □  □ "
//          )
//      )
//      field = controller.put(Move(Stone("peg"), 0, 1))
//      field.toString should be(
//        " \uE008     □       " + eol +
//        " \uE008  □  □  □  □ " + eol +
//        " □           □ " + eol +
//        " □  □  □  □  □ "
//      )
//      field = controller.undo
//      field.toString should be(
//        " \uE008     □       " + eol +
//        " □  □  □  □  □ " + eol +
//        " □           □ " + eol +
//        " □  □  □  □  □ "
//      )
//      field = controller.redo
//      field.toString should be(
//        " \uE008     □       " + eol +
//          " \uE008  □  □  □  □ " + eol +
//          " □           □ " + eol +
//          " □  □  □  □  □ "
//      )
//    }
//  }
//}