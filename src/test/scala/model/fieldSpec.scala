package model
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class fieldSpec extends AnyWordSpec {
  val eol = System.getProperty("line.separator")

  "Malefiz" should {
    "return List of ints equally spaced" in {
      linspace(0, 10, 3) should be (List(0, 4, 8))
    }

    "have a scaleable field" in {
      listProjector(List(0, 4, 8)) should be(" □           □           □ ")
      listProjector(List(0, 7, 14)) should be(" □                    □                    □ ")
      listProjector(List(0, 1, 2)) should be(" □  □  □ ")
    }
    "have scaleable spaces" in {
      borderSpace(20, 5) should be("                     ")
      borderSpace(5, 2) should be("   ")
      borderSpace(17, 4) should be("                  ")
    }
    "have centered fields" in {
      centerPath(20, 5) should be("                      □  □  □  □  □                      " + eol)
      centerPath(5, 2) should be("    □  □    " + eol)
      centerPath(17, 4) should be("                   □  □  □  □                   " + eol)
    }
    "have a Path" in {
      vPath(20, 5, 3) should be("                      □     □     □                      " + eol)
      vPath(5, 2, 1) should be("    □    " + eol)
      vPath(17, 4, 4) should be("                   □     □     □                   " + eol)
    }
    "have a block" in {
      blockLayer(20, 5, 3) should be("                      □  □  □  □  □                      " + eol + "                      □     □     □                      " + eol)
      blockLayer(5, 2, 1) should be("    □  □    " + eol + "    □    " + eol)
      blockLayer(17, 4, 4) should be("                   □  □  □  □                   " + eol + "                   □     □     □                   " + eol)
    }
    "have a scalable playing field" in {
      var player = 4;
      var width = player * 4 + 1
      buildPyramid(width, player).toString() should be(
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
      buildPyramid(width, player).toString() should be(
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
      buildPyramid(width, player).toString() should be(
        "             □             " + eol +
        "       □  □  □  □  □       " + eol +
        "       □           □       " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol +
        " □           □           □ " + eol +
        " □  □  □  □  □  □  □  □  □ " + eol)
    }
  }

}
