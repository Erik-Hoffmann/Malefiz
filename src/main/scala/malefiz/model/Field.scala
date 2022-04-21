package malefiz.model
import malefiz.model.Space

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
class Field(players: Int) {
  val eol = System.getProperty("line.separator")

  def linspace(a: Int, b: Int, num: Int): List[Int] =
    if (num == 2) then List(a, b - 1)
    else a.to(b, (b - a) / num + 1).toList

  def listProjector(l: List[Int]): ListBuffer[Space] = {
    var list = new ListBuffer[Space]()
    for (i <- 0 until l.last + 1) {
      if (l.contains(i)) {
        list += new Space(1)
      } else {
        list += new Space(0)
      }
    }
    return list;
  }

  def borderSpace(width: Int, num: Int): ListBuffer[Space] = {
    val list = new ListBuffer[Space]();
    for (i <- 0 until ((width - num) / 2)) {
      list += new Space(0);
    }
    return list;
  }

  def centerPath(width: Int, num: Int): List[Space] = {
    var list = borderSpace(width, num)
    for (i <- 0 until num) {
      list += new Space(1)
    }
    (list ++= borderSpace(width, num)).toList
  }

  def vPath(width: Int, num: Int, v: Int): List[Space] =
    (borderSpace(width, num) ++= listProjector(linspace(0, num, v)) ++= borderSpace(width, num)).toList

  def blockLayer(width: Int, num: Int, v: Int): List[List[Space]] =
    var list: List[List[Space]] = List(centerPath(width, num), vPath(width, num, v))
    return list


  def buildPyramid(width: Int, players: Int): List[List[Space]] = {
    var list = borderSpace(width, 1) ++= ListBuffer(new Space(1)) ++= borderSpace(width, 1)
    var builder: List[List[Space]] = List(list.toList);
    for {i <- players - 1 until 0 by -1} {
      builder = builder ++ blockLayer(width, width - (i * 4), players - i + 1)
    }
    builder = builder ::: blockLayer(width, width, players + 1)
    builder = builder :+ centerPath(width, width)
    return builder
  }
  def render: String = {
    var list = buildPyramid(players*4+1, players)
    var string = new StringBuilder()
    for (l <- list) {
      for (i <- l) {
        string ++= i.toString
      }
      string ++= eol
    }
    return string.toString()
  }
}
