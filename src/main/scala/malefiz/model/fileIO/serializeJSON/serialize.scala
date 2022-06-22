package malefiz
package model
package fileIO
package serializeJSON

import play.api.libs.json.{JsArray, JsNumber, JsObject, Json}
import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}
import scala.io.Source

import controller.ControllerInterface

class serialize extends fileIOInterface {

  override val extension: String = "json"

    def serializeGame(cntrllr: ControllerInterface): JsObject =
      Json.obj(
        "controller" -> Json.obj(
          "diced" -> JsNumber(cntrllr.diced),
          "board" -> Json.obj(
            "players" -> JsArray(
              cntrllr.getBoard.players.map(player => playerToJson(player))
            ),
            "width" -> JsNumber(cntrllr.getBoard.width),
            "height" -> JsNumber(cntrllr.getBoard.height)
          )
        )
      )

    def loadBoard(cntrllr: ControllerInterface): Unit =
      ???

    def loadController(cntrllr: ControllerInterface): Unit =
      ???


    def playerToJson(player: Player): JsObject =
      Json.obj(
        "color" -> player.color.toString,
        "stones" -> player.numPegs,
//        "startField" -> player.,
        "pegs" -> pegsToJSON(player.pegs)
      )

  def pegsToJSON(pegs: Array[Peg]): JsArray =
    ???


  override def dumps(cntrllr: ControllerInterface): Unit =
    val writer = new PrintWriter(new File(fileName))
    writer.write(Json.prettyPrint(serializeGame(cntrllr)))
    writer.close()

  override def loads(cntrllr: ControllerInterface): Unit =
    if (Files.exists(Paths.get(fileName))) {
      val data = Json.parse(Source.fromFile(fileName).getLines.mkString)
      // loadBoard
      // loadController
      cntrllr.notifyObservers()
    }
}
