package malefiz

import com.google.inject.{Guice, Injector}
import malefiz.aview.TUI
import malefiz.aview.gui3d.Gui3d
import malefiz.controller.ControllerInterface
import malefiz.controller.Controller

import scala.io.StdIn.readLine

object Malefiz {

  @main def start(): Unit =
//    val inject:Injector = Guice.createInjector(new GameModule)
    val eol: String = System.getProperty("line.separator")
    println("Malefiz!")

    val players: Int = readLine("Number of Players: ").toInt
//    lazy val board: GameBoardInterface = inject.getInstance(classOf[GameBoard])
//    lazy val controller: ControllerInterface = inject.getInstance(classOf[Controller])
//    lazy val tui = inject.getInstance(classOf[TUI])
    val controller: ControllerInterface = Controller(players)
    val tui = TUI(controller)
    //lazy val gui = inject.getInstance(classOf[GUIStart])
    val gui = new Gui3d(controller)
    val threadGui = new Thread {
      override def run(): Unit = {
        gui.main(Array[String]())
      }
    }
    threadGui.start()
    tui.run()
}