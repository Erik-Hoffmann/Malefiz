package malefiz
package model.fileIO

import controller.ControllerInterface
import malefiz.model.GameBoardInterface

trait fileIOInterface:
  val baseName: String = "save"
  val extension: String
  val fileName: String
  def dumps(gameBoard: GameBoardInterface, currentplayer: Int): Unit
  def loads(): ControllerInterface
