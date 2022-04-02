package model

import scala.io.StdIn.readLine

val eol = System.getProperty("line.separator")
val field = " □ "
val space = "   "
val stone = " ■ " // for later maybe

def linspace(a: Int, b: Int, num: Int): List[Int] =
  if (num == 2) then List(a, b-1)
  else a.to(b, (b-a)/num+1).toList

def listProjector(l: List[Int]) = 0.to(l.last).map(x => if (l.contains(x)) then field else space).mkString("")

def borderSpace(width: Int, num: Int) = space * ((width-num)/2)

def centerPath(width: Int, num: Int) = borderSpace(width, num) + field * num + borderSpace(width, num) + eol

def vPath(width: Int, num: Int, v: Int) =
  borderSpace(width, num) + listProjector(linspace(0, num, v)) + borderSpace(width, num) + eol

def blockLayer(width:Int, num: Int, v: Int) =
  centerPath(width, num) + vPath(width, num, v)


def buildPyramid(width: Int, players: Int) = {
  val builder = new StringBuilder();
  builder.append(borderSpace(width, 1) + field + borderSpace(width, 1)+ eol)
  val innerWidth = (width-5).to(0).by(-4).toList
  for {i <- players-1 until 0 by -1} {
    builder.append(blockLayer(width, width - (i*4), players-i+1))
  }
  builder.append(blockLayer(width, width, players + 1)).append(centerPath(width, width))
}

@main def board(): Unit = {
  val players = readLine("Number of players: ").toInt
  val width = players * 4 + 1
  println(buildPyramid(width, players))
}
