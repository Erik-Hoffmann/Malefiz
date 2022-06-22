package malefiz
package model

trait GameBoardInterface(numPlayers: Int):
  val width: Int = numPlayers * 4 + 1
  val height: Int = numPlayers * 2 + 1
  val board: Array[Array[Ground]] = Array.ofDim[Ground](height, width)
  val players: Array[Player] = Array.tabulate(numPlayers) { n => Player(Colors.fromOrdinal(n)) }

  def buildGame: GameBoardInterface
  def getNumPlayers: Int
  def validateTargetBlocker(x: Int, y: Int): Boolean
  def validateTargetPeg(x: Int, y: Int): Boolean
  def moveBlocker(field: Field): Unit
  def removeStone(field: Field): Unit
  def pegGoHome(peg: Peg): Unit
  def moveStone(src: Field, dest: Field): Option[Stone]
  def checkWin: Boolean

