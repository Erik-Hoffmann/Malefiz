package malefiz

import scala.io.StdIn.readLine

import model.Field
import model.Tokens._

@main def start: Unit =
  val eol: String = System.getProperty("line.separator")
  println("Malefiz!")
  val players = readLine("Number of Players: ").toInt
  val field = new Field(players)
  println(field.render)
