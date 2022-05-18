package malefiz

import scala.io.StdIn.readLine

import aview.TUI
//import controller.PutCommand
import controller.Controller
import model.Gameboard


@main def start(): Unit =
  val eol: String = System.getProperty("line.separator")
  println("Malefiz!")
  val players = readLine("Number of Players: ").toInt
  val board = Gameboard(players)
  val controller = Controller(board.buildBoard())
  val tui = TUI(controller)
  tui.run()
