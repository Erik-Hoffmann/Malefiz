package malefiz
package controller

import util.Observable
import model.Field
import model.Tokens

case class Controller(var field: Field) extends Observable:
//  def doAndPublish(doThis: Move => Field, move: Move): Unit =
//    field = doThis(move)
//    notifyObservers()
//  def doAndPublish(doThis: => Field): Unit =
//    field = doThis
//    notifyObservers()

  override def toString: String = field.toString
