package malefiz
package model

import play.api.libs.json.{JsArray, JsValue, Json}

import java.awt.Color
import scala.xml.{Node, NodeSeq}

case class GameBoard(numPlayers: Int) extends GameBoardInterface(numPlayers):
  val eol: String = sys.props("line.separator")
  val nCoords: Int => String = (i: Int) => if (i<10)s" $i "else s"$i "
  def buildBoard(board: Array[Array[Ground]]): Array[Array[Ground]] =
    for (idx <- board.indices) {
      if idx % 2 == 0 then
        board(idx) = updateRowSpaced(idx, idx / 2 + 1)
      else
        board(idx) = updateRowFilled(idx, idx * 2 + 3)
    }
    board

  def updateRowFilled(row: Int, innerWidth: Int): Array[Ground] =
    (0 until width).map(idx => if idx < (width - innerWidth) / 2 || idx > width - ((width - innerWidth) / 2 + 1) then EmptyGround(row, idx) else Field(row, idx, FreeField())).toArray

  def updateRowSpaced(row: Int, vertices: Int): Array[Ground] =
    (0 until width).map(idx =>
      if idx < (width - (vertices + (vertices - 1) * 3)) / 2 ||
        idx > width - ((width - (vertices + (vertices - 1) * 3)) / 2) ||
        (idx - ((width - (vertices + (vertices - 1) * 3)) / 2)) % 4 != 0
      then EmptyGround(row, idx) else Field(row, idx, FreeField())).toArray

  def placePegs(board: Array[Array[Ground]]): Array[Array[Ground]] =
    players.indices.zip(players).foreach{case(idx, player) =>
      val newPeg = Field(height-1, 2+(idx*4), Peg(player.color));
      board(height-1)(2+(idx*4)) = newPeg; player.pegs(0) = newPeg }
    board

  def placeBlocker(board: Array[Array[Ground]]): Array[Array[Ground]] =
    board.indices.map(idx => if (idx != 0 && idx % 2 == 1 && idx != 1 && idx != height-1)
      board(idx).indices.map(fidx => if (board(idx-1)(fidx).isInstanceOf[Field]) board(idx)(fidx) = Field(idx, fidx, Blocker()))
    )
    board

  override def buildGame: GameBoardInterface =
    buildBoard(board)
    placePegs(board)
    placeBlocker(board)
    this

  override def toString: String =
    "   "+board(0).indices.map(nCoords).mkString("")+eol+
    board.zipWithIndex.map{case (row,idx) => nCoords(idx).toString + row.map(ground => ground.toString).mkString("")}.mkString(s"$eol")

  override def toJson: JsValue =
    Json.obj(
      "players" -> JsArray(players.map(players => players.toJson)),
      "board" -> boardToJson
    )
  def boardToJson: JsArray =
    var arr = new JsArray()
    for (subarr <- board)  {
      var subJsArray = new JsArray()
      for (field <- subarr){
        subJsArray = subJsArray :+ Json.toJson(field.toString)
      }
      arr = arr :+ subJsArray
    }
    arr

  override def fromJson(js: JsValue): GameBoardInterface =
    boardFromJson((js \ "board").get)
    playersFromJson((js \ "players").get)
    this

  def playersFromJson(value: JsValue): Unit =
    var playerArray = value.as[JsArray]
    for (p <- players)
      for (pegs <- p.getPegs)
        p.removePeg(pegs.x, pegs.y)
    for (i <- 0 until players.length)
      val coord = (playerArray(i) \ "pegs").as[JsArray].value
      for (peg <- coord) {
        val field = Field((peg \ "x").get.toString.toInt, (peg \ "y").get.toString.toInt, Peg(players(i).color))
        players(i).pegs(players(i).getPegs.length) = field
        board((peg \ "x").get.toString.toInt)((peg \ "y").get.toString.toInt) = field
      }

  def boardFromJson(jsonBoard: JsValue): Unit =
    val arr = jsonBoard.as[Array[Array[String]]]
    for (i <- arr.indices) {
      for (j <- arr(i).indices){
        board(i)(j) = generateFromString(arr(i)(j), i, j)
      }
    }

  def generateFromString(string: String, x: Int, y: Int): Ground =
    string match
      case "   " =>  EmptyGround(x,y)
      case " □ " => new Field(x,y,FreeField())
      case " ■ " => new Field(x,y,Blocker())
      case _ => new Field(x,y,FreeField())

  override def fromXML(node: Node): GameBoardInterface =
    boardFromXML((node \\ "field").head)
    playersFromXML((node \\ "players").head)
    this

  def boardFromXML(node: NodeSeq): Unit =
    for (i <- (node \\ "Array").indices)
      val subarr = (node \ "Array").apply(i)
      for (j <- (subarr \ "ground").indices)
        board(i)(j) = generateFromStringXML((subarr \ "ground").apply(j).head.text.trim, i, j)

  def generateFromStringXML(string: String, x: Int, y: Int): Ground =
    string match
      case "" =>  EmptyGround(x,y)
      case "□" => new Field(x,y,FreeField())
      case "■" => new Field(x,y,Blocker())
      case _ => new Field(x,y,FreeField())

  def playersFromXML(node: NodeSeq): Unit =
    for (p <- players)
      for (pegs <- p.getPegs)
        p.removePeg(pegs.x, pegs.y)
    for (i <- 0 until players.length)
      val coord = (node \ "pegs").apply(i)
      for (peg <- coord)
        val field = Field((peg \ "x").head.text.trim.toInt, (peg \ "y").head.text.trim.toInt, Peg(players(i).color))
        players(i).pegs(players(i).getPegs.length) = field
        board((peg \ "x").head.text.trim.toInt)((peg \ "y").head.text.trim.toInt) = field


  override def toXML: Node =
    <gameboard>
      {fieldToXML}
      {playersToXML}
    </gameboard>

  def playersToXML:Node =
    <players>{players.map(p => p.toXML)}</players>

  def fieldToXML: Node =
    <field>{board.map(subarr => subArrToXML(subarr))}</field>

  def subArrToXML(arr :Array[Ground]): Node =
   <Array> {arr.map(ground => <ground>{ground.toString}</ground>)}</Array>