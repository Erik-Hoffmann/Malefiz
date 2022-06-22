package malefiz
package model

class Stone(sort: String)

case class Peg(position: Field, color: Colors) extends Stone(" \uE008 ")

case class FreeField() extends Stone(" □ ")

case class Blocker() extends Stone(" ■ ")

case class Empty() extends Stone("   ")

