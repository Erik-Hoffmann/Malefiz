package malefiz

import scala.io.StdIn.readLine
import aview.{GUIStart, TUI}
import controller.ControllerInterface
//import controller.ExecuteTurnCommand
import controller.BaseImpl.Controller
import model.BaseImpl.Gameboard
import model.GameboardInterface

import com.google.inject.{Guice, Injector}
import javax.inject.Inject

object Malefiz {

@main def start(): Unit =
  val inject:Injector = Guice.createInjector(new GameModule)
  val eol: String = System.getProperty("line.separator")
  println("Malefiz!")

  val players: Int = readLine("Number of Players: ").toInt
  lazy val board: GameboardInterface = inject.getInstance(classOf[Gameboard])
  lazy val controller: ControllerInterface = inject.getInstance(classOf[Controller])
  lazy val tui = inject.getInstance(classOf[TUI])
  lazy val gui = inject.getInstance(classOf[GUIStart])
  val threadGui = new Thread {
    override def run(): Unit = {
      gui.main(Array[String]())
    }
  }
  threadGui.start()
  tui.run()
}



