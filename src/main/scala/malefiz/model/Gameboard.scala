package malefiz.model

case class Gameboard(players: Int) extends GameboardTrait(players):
  val playerList: Array[Player] = createPlayers

  def createPlayers: Array[Player] = Array.tabulate(players) {n => new Player(Colors.fromOrdinal(n), 2+(n*4), height) }

  def buildGame(board: Array[Array[Ground]]): Array[Array[Ground]] =
    for (idx <- board.indices) {
      if idx % 2 == 0 then
        board(idx) = updateRowSpaced(idx, idx/2+1)
      else
        board(idx) = updateRowFilled(idx, idx*2+3)
    }
    board

  def exampleUpdate(board: Array[Array[Ground]]): Array[Array[Ground]] =
    board(0) = updateRowFilled(0, 5)
    board

  def updateRowFilled(row: Int, innerWidth: Int): Array[Ground] =
    (0 until width).map(idx => if idx< (width-innerWidth)/2 || idx > width-((width-innerWidth)/2+1) then Field(row,idx,Stone("empty")) else Field(row,idx, Stone("freefield"))).toArray

  def updateRowSpaced(row: Int, vertices: Int): Array[Ground] =
    (0 until width).map(idx =>
      if idx< (width - (vertices+(vertices-1) * 3 ))/2 ||
        idx > width-((width - (vertices+(vertices-1) * 3 ))/2) ||
          (idx - ((width - (vertices+(vertices-1) * 3 ))/2)) % 4 != 0
      then Field(row,idx,Stone("empty")) else Field(row,idx, Stone("freefield"))).toArray

  def dimensions(players: Int): (Int, Int) = (players*4+1, players*2+2)

  def put(field: Field): Unit =
    board(field.x)(field.y) = field
  
  def getField(x: Int,y: Int): Ground = board(x)(y)

  def movePeg(oldField: Field, newField: Field): Array[Array[Ground]] =
    put(Field(oldField.x, oldField.y, Stone("freefield")))
    put(newField)
    board

//  def store(num: Int) =
//    dice = num
//    this

  override def toString: String = board.map(row => row.map(field => field.toString).mkString("")).mkString(eol)

  def buildBoard(): Gameboard =
    buildGame(board)
    this

  def exampleUpdateBoard(): Gameboard =
    exampleUpdate(board)
    this