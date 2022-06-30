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
    val file = scala.xml.XML.loadFile("malefiz.xml")
    val pegs = (file \\ "board" \\ "players" \\ "peg").text.strip
    val grounds = (file \\ "board" \\ "field" \\ "ground").text
    val current = (file \\ "board" \\ "current").text.strip.toInt
    Controller(0)
  }

  override def dumps(board: GameBoardInterface, current: Int): Unit = {
    val pw = new PrintWriter(new File("malefiz.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)

    val xml = prettyPrinter.format(boardToXML(board, board.players, current))
    pw.write(xml)
    pw.close
  }

  def boardToXML(board: GameBoardInterface, players: Array[Player], current: Int) = {
    <board>
      <fields>
        {
        fieldToXML(board)
        }
      </fields>
      <players>
        {
        playersToXML(players)
        }
      </players>
      <playercount>
        {
        board.players.length
        }
      </playercount>
      <current>
        {
        current
        }
      </current>
    </board>
  }

  def fieldToXML(board: GameBoardInterface) = {
    {
      for {
        row <- board.board
        col <- row
      } yield groundToXML(col)
    }
  }

  def groundToXML(ground: Ground) = {
    <ground>
      {
      ground.toString
      }
    </ground>
  }

  def playersToXML(players: Array[Player]) = {
    for {
      x <- players
      y <- x.pegs.filterNot(_ == null)
    } yield pegsToXML(y)

  }

  def pegsToXML(field: Field) = {
    <peg>
      {
      field.getCoords.toString()
      }
    </peg>
  }
}