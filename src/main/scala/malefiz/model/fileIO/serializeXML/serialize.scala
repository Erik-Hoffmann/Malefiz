package malefiz
package model
package fileIO
package serializeXML

import java.io.*
import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.io.Source
import controller.{Controller, ControllerInterface}

import scala.xml.{Node, NodeSeq, PrettyPrinter}

class serialize extends fileIOInterface {

  val extension: String = "xml"
  val fileName: String = s"$baseName.$extension"

  override def loads(): ControllerInterface = {
    val file = scala.xml.XML.loadFile(fileName)
    val xml = (file \\ "game")
    val con = Controller((xml \\ "numplayers").head.text.trim.toInt)
    con.fromXML((xml \\ "gameboard").head,  (xml \ "current").head.text.trim.toInt)
  }

  override def dumps(board: GameBoardInterface, current: Int): Unit = {
    val pw = new PrintWriter(new File(fileName))
    val prettyPrinter = new PrettyPrinter(120, 4)
    pw.write(prettyPrinter.format(serialize(board,  current)))
    pw.close
  }
  def serialize(gameBoard: GameBoardInterface, currentplayer: Int): Node =
    <game>
      <current>
        {currentplayer}
      </current>
      <numplayers>
        {gameBoard.players.length}
      </numplayers>
        {gameBoard.toXML}
    </game>
}