package malefiz

import scala.io.StdIn.readLine
import aview.TUI
import controller.Controller
import model.Tokens
import model.Field
import model.Matrix

@main def start(): Unit =
  val eol: String = sys.props("line.separator")
  println("Malefiz!")
  val players = readLine("Number of Players: ").toInt
  val field = new Field(players, Tokens.field)
  val controller = Controller(field)
  val tui = TUI(controller)
  println(tui.run())