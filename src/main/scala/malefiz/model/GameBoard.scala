package malefiz
package model

case class GameBoard(numPlayers: Int) extends GameBoardInterface(numPlayers):
  val eol: String = sys.props("line.separator")

  def createPlayers: Array[Player] = Array.tabulate(numPlayers) { n => new Player(Colors.fromOrdinal(n)) }

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
    (0 until width).map(idx => if idx < (width - innerWidth) / 2 || idx > width - ((width - innerWidth) / 2 + 1) then Field(row, idx, Empty()) else Field(row, idx, FreeField())).toArray

  def updateRowSpaced(row: Int, vertices: Int): Array[Ground] =
    (0 until width).map(idx =>
      if idx < (width - (vertices + (vertices - 1) * 3)) / 2 ||
        idx > width - ((width - (vertices + (vertices - 1) * 3)) / 2) ||
        (idx - ((width - (vertices + (vertices - 1) * 3)) / 2)) % 4 != 0
      then Field(row, idx, Empty()) else Field(row, idx, FreeField())).toArray

  def checkWin: Boolean = ???
  def createBoard: GameBoardInterface = ???
  def getNumPlayers: Int = ???
  def moveBlocker(field: Field): Unit = ???
  def moveStone
  (src: Field, dest: Field):
  Option[Stone] = ???
  def pegGoHome(peg: Peg): Unit = ???
  def removeStone(field: Field): Unit = ???
  def validateTargetBlocker(x: Int, y: Int): Boolean = ???
  def validateTargetPeg(x: Int, y: Int): Boolean = ???

  override def buildGame: GameBoardInterface =
    buildBoard(board)
    // TODO: hier blocker platzieren
    // TODO: player initialization
    this
