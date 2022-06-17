package malefiz
package model.BaseImpl

import model.*

case class Gameboard(players: Int) extends GameboardInterface(players) :
  val eol: String = sys.props("line.separator")
  val board: Array[Array[Ground]] = Array.ofDim[Ground](height, width)

  def createPlayers: Array[Player] = Array.tabulate(players) { n => new Player(Colors.fromOrdinal(n), 2 + (n * 4), height) }

  def buildGame(board: Array[Array[Ground]]): Array[Array[Ground]] =
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
    (0 until width).map(idx => if idx < (width - innerWidth) / 2 || idx > width - ((width - innerWidth) / 2 + 1) then Field(row, idx, Stone(Fields.Empty)) else BaseImpl.Field(row, idx, Stone(Fields.FreeField))).toArray

  def updateRowSpaced(row: Int, vertices: Int): Array[Ground] =
    (0 until width).map(idx =>
      if idx < (width - (vertices + (vertices - 1) * 3)) / 2 ||
        idx > width - ((width - (vertices + (vertices - 1) * 3)) / 2) ||
        (idx - ((width - (vertices + (vertices - 1) * 3)) / 2)) % 4 != 0
      then BaseImpl.Field(row, idx, Stone(Fields.Empty)) else BaseImpl.Field(row, idx, Stone(Fields.FreeField))).toArray

  def dimensions(players: Int): (Int, Int) = (players * 4 + 1, players * 2 + 2)

  def put(stone: Stone, x: Int, y: Int): GameboardInterface =
    board(x)(y) = Field(x, y, stone)
    this

  def switchPos(srcPos: Option[(Int, Int)], destPos: (Int, Int)): GameboardInterface =
    if (srcPos.isEmpty) {
      putNewPeg(destPos)
      return this
    }
    val temp: Ground = getField(srcPos.get._1, srcPos.get._2)
    board(srcPos.get._1)(srcPos.get._2) = getField(destPos._1, destPos._2)
    board(destPos._1)(destPos._2) = temp
    this

  def getField(x: Int, y: Int): Ground = board(x)(y)

  def putNewPeg(destPos: (Int, Int)): Unit = {
    currentPlayer.pegs :+= destPos
    board(destPos._1)(destPos._2) = BaseImpl.Field(destPos._1, destPos._2, Stone.apply(Fields.Peg))
  }

  def undoPutPeg(destPos: (Int, Int)): GameboardInterface = {
    currentPlayer.pegs = currentPlayer.pegs.take(currentPlayer.pegs.indexOf(destPos))
    board(destPos._1)(destPos._2) = Field(destPos._1, destPos._2, Stone.apply(Fields.FreeField))
    this
  }

  def legalMove(src: (Int, Int), dest: (Int,Int), countMoves: Int): Boolean = {
    if (checkEmpty(dest)) {
      return false
    } else if (checkBlocker(dest) && countMoves != 0){
      return false
   }
    true
  }
  
  def removeBlocker(pos: (Int,Int)): GameboardInterface = {
    board (pos._1)(pos._2) = Field(pos._1, pos._2, Stone.apply(Fields.FreeField))
    this
  }

  def sendPegHome(pos: (Int,Int)): GameboardInterface = {
    for(p <- playerList) {
      for (i <- p.pegs){
        if (pos.equals(i)) {
          p.pegs.remove(p.pegs.indexOf(i))
        }
      }
    }
    this
  }
  //  def movePeg(oldField: Field, newField: Field): Array[Array[Ground]] =
  //    put(oldField.x, oldField.y, Stone(Fields.FreeField))
  //    put(newField.x, newField.y)
  //    board

  //  def store(num: Int) =
  //    dice = num
  //    this

  override def toString: String = board.map(row => row.map(field => field.toString).mkString("")).mkString(eol)

  def buildBoard(): GameboardInterface =
    buildGame(board)
    this

  def exampleUpdateBoard(): GameboardInterface =
    exampleUpdate(board)
    this

  def checkPeg(pos: (Int, Int)): Boolean = {
    val f: Field = getField(pos._1, pos._2).asInstanceOf[Field]
    f.stone match
      case p: Peg => true
      case _ => false
  }

  def checkEmpty(pos: (Int, Int)): Boolean = {
    val f: Field = getField(pos._1, pos._2).asInstanceOf[Field]
    f.stone match
      case e: Empty => true
      case _ => false
  }

  def checkBlocker(pos: (Int, Int)): Boolean = {
    val f: Field = getField(pos._1, pos._2).asInstanceOf[Field]
    f.stone match
      case p: Blocker => true
      case _ => false
  }

  def checkFreeField(pos: (Int, Int)): Boolean = {
    val f: Field = getField(pos._1, pos._2).asInstanceOf[Field]
    f.stone match
      case p: FreeField => true
      case _ => false
  }
