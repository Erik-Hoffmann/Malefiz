package malefiz
package model
package fileIO
package serializeJSON

import play.api.libs.json.{JsArray, JsNumber, JsObject, JsValue, Json}

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.io.Source
import controller.{Controller, ControllerInterface}

class serialize extends fileIOInterface {

  override val extension: String = "json"
  override val fileName: String = s"$baseName.$extension"

  def serializeGame(gameBoard: GameBoardInterface, currentPlayer: Int): JsValue =
    Json.obj(
      "currentplayer" -> currentPlayer,
      "numPlayers" -> gameBoard.players.length,
      "Gameboard" -> gameBoard.toJson)


  override def dumps(gameBoard: GameBoardInterface, currentplayer: Int): Unit =
    val writer = new PrintWriter(new File(fileName))
    writer.write(Json.prettyPrint(serializeGame(gameBoard, currentplayer)))
    writer.close()

  override def loads(): ControllerInterface =
    val data = Json.parse(Source.fromFile(fileName).getLines.mkString)
    val gameBoardJson = (data \ "Gameboard").get
    val cntrllr = Controller((data \ "numPlayers").get.toString.toInt)
    cntrllr.fromJson(gameBoardJson, (data \ "currentplayer").get.toString.toInt)
    cntrllr
}
