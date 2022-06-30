package malefiz
package model
package fileIO
package serializeXML

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.io.Source
import controller.{Controller, ControllerInterface}

class serialize extends fileIOInterface {
  override val extension: String = ".json"
  override val fileName: String = baseName + extension
  def dumps(gameBoard: GameBoardInterface, currentplayer: Int): Unit = ???
  def loads(): ControllerInterface = ???
}