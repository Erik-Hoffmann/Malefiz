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
  override val fileName: String = baseName + extension

  def serializeGame(cntrllr: ControllerInterface): JsValue =
    Json.obj("controller" -> cntrllr.toJson)

  def loadBoard(jsonBoard: JsValue, players: Int): GameBoard =
    val gameBoard = GameBoard(players)
    val arr = (jsonBoard \ "board").as[Array[Array[String]]]
    for (i <- 0 until arr.length) {
      for (j <- 0 until arr(i).length){
        gameBoard.board(i)(j) = generateFromString(arr(i)(j), i, j)
      }
    }
    gameBoard


  def loadPlayers(playerJson: JsValue, controller: ControllerInterface): Array[Player] = ???

  def generateFromString(string: String, x: Int, y: Int): Ground =
    string match
      case "   " =>  EmptyGround(x,y)
      case " □ " => Field(x,y,FreeField())
      case " ■ " => Field(x,y,Blocker())

  def loadController(json: JsValue, controller: ControllerInterface): Controller = ???

  def boardToJson(array: Array[Array[Ground]]): JsArray =
    JsArray(array.map(row => JsArray(row.map(field => Json.parse(field.toString)))))

  def playerToJson(player: Player): JsObject =
    Json.obj(
      "color" -> player.color.toString,
      "startField" -> player.startField,
      "pegs" -> pegsToJSON(player.pegs)
    )

  def pegsToJSON(pegs: Array[Field]): JsArray =
    JsArray(pegs.filterNot(_==null).map(peg => Json.obj("x"->JsNumber(peg.x), "y" -> JsNumber(peg.y))))

  override def dumps(cntrllr: ControllerInterface): Unit =
    val writer = new PrintWriter(new File(fileName))
    writer.write(Json.prettyPrint(serializeGame(cntrllr)))
    writer.close()

  override def loads(cntrllr: ControllerInterface): Unit =
    if (Files.exists(Paths.get(fileName))) {
      val data = Json.parse(Source.fromFile(fileName).getLines.mkString)
//      loadPlayers()
      // loadController
      cntrllr.notifyObservers()
    }
}
