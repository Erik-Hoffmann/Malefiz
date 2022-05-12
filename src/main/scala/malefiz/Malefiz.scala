package malefiz

import scala.io.StdIn.readLine

import aview.TUI
import controller.PutCommand
import controller.Controller
import model.Field
import model.Token

@main def start: Unit =
  val eol: String = System.getProperty("line.separator")
  println("Malefiz!")
  val players = readLine("Number of Players: ").toInt
  val field = new Field(players, Token("field"))
  val controller = Controller(field)
  val tui = TUI(controller)
  println(tui.run)
