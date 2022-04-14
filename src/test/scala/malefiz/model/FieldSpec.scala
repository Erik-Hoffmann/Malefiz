package malefiz.model


import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class FieldSpec extends AnyWordSpec {
  val eol = System.getProperty("line.separator")

  "Malefiz" should {
    val test = new Field(0)
    "return List of ints equally spaced" in {
      test.linspace(0, 10, 3) should be (List(0, 4, 8))
    }

    "have a scaleable field" in {
      test.listProjector(List(0, 4, 8)) should be(" □           □           □ ")
      test.listProjector(List(0, 7, 14)) should be(" □                    □                    □ ")
      test.listProjector(List(0, 1, 2)) should be(" □  □  □ ")
    }
    "have scaleable spaces" in {
      test.borderSpace(20, 5) should be("                     ")
      test.borderSpace(5, 2) should be("   ")
      test.borderSpace(17, 4) should be("                  ")
    }
    "have centered fields" in {
      test.centerPath(20, 5) should be("                      □  □  □  □  □                      " + eol)
      test.centerPath(5, 2) should be("    □  □    " + eol)
      test.centerPath(17, 4) should be("                   □  □  □  □                   " + eol)
    }
    "have a Path" in {
      test.vPath(20, 5, 3) should be("                      □     □     □                      " + eol)
      test.vPath(5, 2, 1) should be("    □    " + eol)
      test.vPath(17, 4, 4) should be("                   □     □     □                   " + eol)
    }
    "have a block" in {
      test.blockLayer(20, 5, 3) should be("                      □  □  □  □  □                      " + eol + "                      □     □     □                      " + eol)
      test.blockLayer(5, 2, 1) should be("    □  □    " + eol + "    □    " + eol)
      test.blockLayer(17, 4, 4) should be("                   □  □  □  □                   " + eol + "                   □     □     □                   " + eol)
    }
    "have a scalable playing field" in {
      var player = 4;
      var width = player * 4 + 1
      test.buildPyramid(width, player) should be(
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
      test.buildPyramid(width, player) should be(
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
      test.buildPyramid(width, player).toString() should be(
        "             □             " + eol +
        "       □  □  □  □  □       " + eol +
        "       □           □       " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol +
        " □           □           □ " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol)
    }
  }
}
