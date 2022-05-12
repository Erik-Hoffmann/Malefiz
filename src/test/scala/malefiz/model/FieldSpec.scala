package malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  val eol = System.getProperty("line.separator")

  "Malefiz" should {
    var test = new oldField(4)
    "have a scalable playing field" in {
      test.render should be(
          "                         □                         " + eol +
          "                   □  □  □  □  □                   " + eol +
          "                   □           □                   " + eol +
          "             □  □  □  □  □  □  □  □  □             " + eol +
          "             □           □           □             " + eol +
          "       □  □  □  □  □  □  □  □  □  □  □  □  □       " + eol +
          "       □           □           □           □       " + eol +
          " □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □ " + eol +
          " □           □           □           □           □ " + eol +
          " □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □ " + eol)
      test = new oldField(7)
      test.render should be(
          "                                           □                                           " + eol +
          "                                     □  □  □  □  □                                     " + eol +
          "                                     □           □                                     " + eol +
          "                               □  □  □  □  □  □  □  □  □                               " + eol +
          "                               □           □           □                               " + eol +
          "                         □  □  □  □  □  □  □  □  □  □  □  □  □                         " + eol +
          "                         □           □           □           □                         " + eol +
          "                   □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □                   " + eol +
          "                   □           □           □           □           □                   " + eol +
          "             □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □             " + eol +
          "             □           □           □           □           □           □             " + eol +
          "       □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □       " + eol +
          "       □           □           □           □           □           □           □       " + eol +
          " □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □ " + eol +
          " □           □           □           □           □           □           □           □ " + eol +
          " □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □  □ " + eol)
      test = new oldField(2)
      test.render should be(
        "             □             " + eol +
        "       □  □  □  □  □       " + eol +
        "       □           □       " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol +
        " □           □           □ " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol)
    }
  }
}
