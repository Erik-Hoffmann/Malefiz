package malefiz
package model

trait GameBoardInterface(numPlayers: Int):
  val width: Int = numPlayers * 4 + 1
  val height: Int = numPlayers * 2 + 2
  val board: Array[Array[Ground]] = Array.ofDim[Ground](height, width)
  val players: Array[Player] = Array.tabulate(numPlayers) { n => Player(Colors.fromOrdinal(n), (2+(n*4), height)) }

  def buildGame: GameBoardInterface
  def getNumPlayers: Int
  def validateTargetBlocker(x: Int, y: Int): Boolean
  def validateTargetPeg(x: Int, y: Int): Boolean
  def moveBlocker(field: Field): Unit
  def removeStone(field: Field): Unit
  def placePegs(board: Array[Array[Ground]]): Array[Array[Ground]]
  def pegGoHome(peg: Peg): Unit
  def moveStone(src: Field, dest: Field): Option[Stone]
  def checkWin: Boolean
  def toString: String

