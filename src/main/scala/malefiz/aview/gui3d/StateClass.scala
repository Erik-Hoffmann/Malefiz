package malefiz.aview.gui3d

class StateClass {
  var state = State.set
}
enum State() {
  case undo
  case redo
  case set
  case move
  case choose
}
