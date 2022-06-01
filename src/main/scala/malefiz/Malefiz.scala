package malefiz

import scala.io.StdIn.readLine
import aview.TUI
import aview.gui.GUI
import malefiz.controller.BaseImpl
//import controller.ExecuteTurnCommand
import malefiz.controller.BaseImpl.Controller
import malefiz.model.BaseImpl.Gameboard


@main def start(): Unit =
  val eol: String = System.getProperty("line.separator")
  println("Malefiz!")
  val players = readLine("Number of Players: ").toInt
  val board = Gameboard(players)
  val controller = BaseImpl.Controller(board)
  val tui = TUI(controller)
  val gui = GUI(controller)
  val threadGui = new Thread {
    override def run: Unit = {
      gui.main(Array[String]())
    }
  }
  threadGui.start()
  tui.run()



