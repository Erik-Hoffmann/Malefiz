package malefiz.model

trait GameboardTrait(players: Int) {

  val (width, height) = dimensions(players)

  val eol: String = sys.props("line.separator")

  val board: Array[Array[Ground]] = Array.ofDim[Ground](height, width)


  def dimensions(players: Int): (Int, Int)

  def createPlayers: Array[Player]

  def buildGame(board: Array[Array[Ground]]): Array[Array[Ground]]

  def updateRowFilled(row: Int, innerWidth: Int): Array[Ground]

  def updateRowSpaced(row: Int, vertices: Int): Array[Ground]

  def buildBoard(): Gameboard

  def put(stone: Stone, x:Int,y:Int): Gameboard

  override def toString: String
}
