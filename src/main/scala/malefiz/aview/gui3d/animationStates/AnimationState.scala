package malefiz
package aview.gui3d.animationStates

import aview.gui3d.Gui3d

abstract class AnimationState {
 def playBefore(gui :Gui3d): Unit
 def playAfter(gui :Gui3d): Unit
}

enum State() {
  case undo
  case redo
  case set
  case move
  case choose
}
