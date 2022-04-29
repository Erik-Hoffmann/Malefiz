package malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {
  val eol: String = System.getProperty("line.separator")

  "Malefiz" should {
    val field = new Field(1, Tokens.field)

    "return List of ints equally spaced" in {
      field.linSpace(0, 10, 3) should be (List(0, 4, 8))
    }

    "have a scaleable field" in {
      field.listProjector(List(0, 4, 8)) should be(" □           □           □ ")
      field.listProjector(List(0, 7, 14)) should be(" □                    □                    □ ")
      field.listProjector(List(0, 1, 2)) should be(" □  □  □ ")
    }
    "have scaleable spaces" in {
      field.borderSpace(20, 5) should be("                     ")
      field.borderSpace(5, 2) should be("   ")
      field.borderSpace(17, 4) should be("                  ")
    }
    "have centered fields" in {
      field.centerPath(20, 5) should be("                      □  □  □  □  □                      " + eol)
      field.centerPath(5, 2) should be("    □  □    " + eol)
      field.centerPath(17, 4) should be("                   □  □  □  □                   " + eol)
    }
    "have a Path" in {
      field.vPath(20, 5, 3) should be("                      □     □     □                      " + eol)
      field.vPath(5, 2, 1) should be("    □    " + eol)
      field.vPath(17, 4, 4) should be("                   □     □     □                   " + eol)
    }
    "have a block" in {
      field.blockLayer(20, 5, 3) should be("                      □  □  □  □  □                      " + eol + "                      □     □     □                      " + eol)
      field.blockLayer(5, 2, 1) should be("    □  □    " + eol + "    □    " + eol)
      field.blockLayer(17, 4, 4) should be("                   □  □  □  □                   " + eol + "                   □     □     □                   " + eol)
    }
    "have a scalable playing field" in {
      var player = 4;
      var width = player * 4 + 1
      field.buildPyramid(width, player) should be(
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
      player = 7
      width = player * 4 + 1
      field.buildPyramid(width, player) should be(
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
      player = 2
      width = player * 4 + 1
      field.buildPyramid(width, player) should be(
        "             □             " + eol +
        "       □  □  □  □  □       " + eol +
        "       □           □       " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol +
        " □           □           □ " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol)
    }
  }
}
