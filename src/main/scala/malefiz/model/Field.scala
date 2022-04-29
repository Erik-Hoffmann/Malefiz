package malefiz
package model

case class Field(matrix: Matrix[Tokens]):
  def this(size: Int, filling: Tokens) = this(new Matrix(size, filling))

  val width: Int = matrix.width
  val height: Int = matrix.height
  val eol: String = sys.props("line.separator")

  def linSpace(a: Int, b: Int, num: Int): List[Int] =
    if num == 2 then List(a, b - 1)
    else a.to(b, (b - a) / num + 1).toList

  def listProjector(l: List[Int]): String = 0.to(l.last).map(x => if l.contains(x) then " □ " else "   ").mkString("")

  def borderSpace(width: Int, num: Int): String = "   " * ((width - num) / 2)

  def centerPath(width: Int, num: Int): String = borderSpace(width, num) + " □ " * num + borderSpace(width, num) + eol

  def vPath(width: Int, num: Int, v: Int): String =
    borderSpace(width, num) + listProjector(linSpace(0, num, v)) + borderSpace(width, num) + eol

  def blockLayer(width: Int, num: Int, v: Int): String =
    centerPath(width, num) + vPath(width, num, v)

  def buildPyramid(width: Int, players: Int): String =
    val levels = new Array[String](players + 1)
    levels(players) = borderSpace(width, 1) + " □ " + borderSpace(width, 1) + eol
    val innerWidth = (width - 5).to(0).by(-4).toList
    for {i <- players - 1 until 0 by -1} {
      levels(i) = blockLayer(width, width - (i * 4), players - i + 1)
    }
    levels(0) = blockLayer(width, width, players + 1) + centerPath(width, width)
    levels.reverse.mkString("")


  override def toString: String =
    buildPyramid(width, (width-1)/4)