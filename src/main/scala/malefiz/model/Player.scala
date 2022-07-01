package malefiz
package model

import Colors.default
import play.api.libs.json.{JsArray, JsNumber, JsValue, Json}

import java.awt.Color
import scala.xml.Node

case class Player(color: Colors, startField: (Int, Int)):
  val numPegs: Int = 5
  var pegs: Array[Field] = Array.ofDim[Field](5)
  def updatePeg(source: Field, dest: Field): Unit = pegs(pegs.indexOf(source)) = dest
  def getPegs: Array[Field] = pegs.filterNot(_==null)
  def getPegByPos(x: Int, y: Int): Option[Field] = if (!getPegs.exists(_.getCoords.equals((x, y)))) None else Option(getPegs.filter(_.getCoords.equals((x,y)))(0))
  def removePeg(x: Int , y:Int): Unit = pegs(pegs.indexOf(getPegByPos(x,y).get)) = null
  override def toString: String = ""+color+"\uE008"+default
  def toJson: JsValue =
    Json.obj(
      "pegs" -> pegsToJSON
    )
    
  def pegsToJSON: JsArray =
    var jsArray  = new JsArray()
    for(field <- pegs) {
      if(field == null) {}
      else
        jsArray = jsArray :+ Json.obj(
          "x" -> JsNumber(field.x),
          "y" -> JsNumber(field.y)
        )
    }
    jsArray
  def toXML:Node =
    <pegs>{getPegs.map(f => <x>{f.x}</x> <y>{f.y}</y>)}</pegs>
