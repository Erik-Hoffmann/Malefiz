package malefiz
package controller

enum State(display: String):
  override def toString: String = display
  case Output extends State("Output")
  case SetPlayer extends State("SetPlayer")
  case ChoosePeg extends State("ChoosePeg")
  case DefineMoves extends State("DefineMoves")
  case ChooseBlocker extends State("ChooseBlocker")