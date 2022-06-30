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

  def serializeGame(cntrllr: ControllerInterface): JsObject =
    Json.obj(
      "controller" -> Json.obj(
        "numPlayers" -> JsNumber(cntrllr.getBoard.players.length),
        "currentPlayer" -> JsNumber(cntrllr.getBoard.players.indexOf(cntrllr.currentPlayer)),
        "GameBoard" -> Json.obj(
          "players" -> JsArray(
            cntrllr.getBoard.players.map(player => playerToJson(player))
          ),
          "board" -> boardToJson(cntrllr.getBoard.board)
        )
      )
    )

  def loadBoard(jsonBoard: JsValue, players: Int): GameBoard =
    val gameBoard = GameBoard(players)
    val arr = (jsonBoard \ "board").as[Array[Array[String]]]
    for (i <- 0 until arr.length) {
      for (j <- 0 until arr(i).length){
        gameBoard.board(i)(j) = generateFromString(arr(i)(j), i, j)
      }
    }
    gameBoard


  def loadPlayers(playerJson: JsValue): Array[Player] = ???


  def generateFromString(string: String, x: Int, y: Int): Ground =
    string match
      case "   " =>  EmptyGround(x,y)
      case " □ " => new Field(x,y,FreeField())
      case " ■ " => new Field(x,y,Blocker())
      case _ => ???



  def loadController(json: JsValue): Controller = ???


  def boardToJson(array: Array[Array[Ground]]): JsArray =
    val arr = new JsArray()
    for (subarr <- array)  {
      val subJsArray = new JsArray()
      for (field <- subarr){
        subJsArray.append(Json.parse(field.toString))
      }
      arr.append(subJsArray)
    }
    arr

  def playerToJson(player: Player): JsObject =
    Json.obj(
      "color" -> player.color.toString,
      "startField" -> player.startField,
      "pegs" -> pegsToJSON(player.pegs)
    )

  def pegsToJSON(pegs: Array[Field]): JsArray =
    val jsArray  = new JsArray()
    for(field <- pegs) {
      if(field == null) {}
      else
        jsArray.append(Json.obj(
          "x" -> JsNumber(field.x),
        "y" -> JsNumber(field.y)
        ))
    }
    jsArray

  override def dumps(cntrllr: ControllerInterface): Unit =
    val writer = new PrintWriter(new File(fileName))
    writer.write(Json.prettyPrint(serializeGame(cntrllr)))
    writer.close()

  override def loads(cntrllr: ControllerInterface): Unit =
    if (Files.exists(Paths.get(fileName))) {
      val data = Json.parse(Source.fromFile(fileName).getLines.mkString)
      // loadController
      cntrllr.notifyObservers()
    }
}
