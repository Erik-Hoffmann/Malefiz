package malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  val eol = System.getProperty("line.separator")

  "Malefiz" should {
    var test = new Field(4)
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
      test = new Field(7)
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
      test = new Field(2)
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
