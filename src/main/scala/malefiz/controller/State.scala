package malefiz
package controller

enum State(display: String):
  override def toString: String = display
  case Output extends State("Output")
  case Failure extends State("Failure")
  case ChoosePeg extends State("ChoosePeg")
  case ChosePegSuccess extends State("ChosePegSuccess")
  case ChooseDest extends State("ChooseDest")
  case ChooseDestSuccess extends State("ChooseDestSuccess")
  case MoveSuccess extends State("MoveSuccess")
  case ChooseBlockerTarget extends State("ChooseBlockerTarget")
  case MoveComplete extends State("MoveComplete")
  case Win extends State("Win")