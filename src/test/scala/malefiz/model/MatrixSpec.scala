package malefiz.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of something. A Matrix" when {
    "empty " should {
      "be created by using a dimension and a sample cell" in {
        val matrix = new Matrix[String](2, "x")
        matrix.width should be(9)
        matrix.height should be(5)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector("x")))
        testMatrix.width should be(1)
        testMatrix.height should be(1)
      }
    }
    "filled" should {
      val matrix = new Matrix[String](2, "x")
      "give access to its cells" in {
        matrix.getCell(0, 0) should be("x")
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, "o")
        matrix.getCell(0, 0) should be("x")
        returnedMatrix.getCell(0, 0) should be("o")
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill("x")
        returnedMatrix.getCell(0, 0) should be("x")
      }
    }
  }
}
