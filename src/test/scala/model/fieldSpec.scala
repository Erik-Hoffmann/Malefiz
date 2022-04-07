package model
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class fieldSpec extends AnyWordSpec {

  "Malefiz" should {
    " " in {
      linspace(0, 10, 3) should be (List(0, 4, 8))
    }

    "have a scaleable field" in {
      listProjector(List(0, 4, 8)) should be(" □           □           □ ")
      listProjector(List(0, 7, 14)) should be(" □                    □                    □ ")
      listProjector(List(0, 1, 2)) should be(" □  □  □ ")
    }
    "have a scaleable spaces" in {
      borderSpace(20, 5) should be("                     ")
      borderSpace(5, 2) should be("   ")
      borderSpace(17, 4) should be("                  ")
    }

  }

}
