package malefiz
package model

import play.api.libs.json.{JsArray, JsValue, Json}

import java.awt.Color

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

  def exampleUpdate(board: Array[Array[Ground]]): Array[Array[Ground]] =
    board(0) = updateRowFilled(0, 5)
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

  def checkWin: Boolean = ???
  def createBoard: GameBoardInterface = ???
  def getNumPlayers: Int = ???
  def moveBlocker(field: Field): Unit = ???
  def moveStone(src: Field, dest: Field): Option[Stone] = ???
  def pegGoHome(peg: Peg): Unit = ???
  def removeStone(field: Field): Unit = ???
  def validateTargetBlocker(x: Int, y: Int): Boolean = ???
  def validateTargetPeg(x: Int, y: Int): Boolean = ???

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

  def playersFromJson(value: JsValue): Unit = ???

  def boardFromJson(jsonBoard: JsValue): Unit =
    val arr = jsonBoard.as[Array[Array[String]]]
    for (i <- 0 until arr.length) {
      for (j <- 0 until arr(i).length){
        board(i)(j) = generateFromString(arr(i)(j), i, j)
      }
    }

  def generateFromString(string: String, x: Int, y: Int): Ground =
    string match
      case "   " =>  EmptyGround(x,y)
      case " □ " => new Field(x,y,FreeField())
      case " ■ " => new Field(x,y,Blocker())
      case _ => ???