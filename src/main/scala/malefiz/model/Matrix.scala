package malefiz
package model

case class Matrix[A](rows: Vector[Vector[A]]):
  def this(players: Int, filling: A) = this(Vector.tabulate(players*4+1, players*2+1) { (x,y) => filling })
  val width: Int = rows.size
  val height: Int = rows(0).size
  def getCell(row: Int, col: Int): A = rows(row)(col)
  def getRow(row: Int): Vector[A] = rows(row)
  def fill(filling: A): Matrix[A] = copy(Vector.tabulate(width, height) {(x,y) => filling})
  def replaceCell(row: Int, col: Int, fill: A): Matrix[A] = copy(rows.updated(row, rows(row).updated(col, fill)))
