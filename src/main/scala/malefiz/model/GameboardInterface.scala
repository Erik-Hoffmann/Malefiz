package malefiz
package model

import play.api.libs.json.JsValue

import scala.xml.Node


trait GameBoardInterface(numPlayers: Int):
  val width: Int = numPlayers * 4 + 1
  val height: Int = numPlayers * 2 + 2
  val board: Array[Array[Ground]] = Array.ofDim[Ground](height, width)
  val players: Array[Player] = Array.tabulate(numPlayers) { n => Player(Colors.fromOrdinal(n), (2+(n*4), height)) }

  def buildGame: GameBoardInterface
  def placePegs(board: Array[Array[Ground]]): Array[Array[Ground]]
  def toString: String
  def toJson: JsValue
  def fromJson(js: JsValue): GameBoardInterface
  def toXML: Node
  def fromXML(node: Node): GameBoardInterface
