package malefiz.aview.gui3d.animationStates

import malefiz.aview.gui3d.Gui3d
import scalafx.animation.FadeTransition
import scalafx.Includes.*
import scalafx.scene.Node
import scalafx.util.Duration

class UndoRedoState extends AnimationState {
  override def playAfter(gui: Gui3d): Unit = {
    println(gui.cameraBody)
    fadeIn(gui)
  }
  def fadeIn(gui: Gui3d): Unit = {
    new FadeTransition {
      fromValue = 0.01
      toValue = 1.0
      duration = Duration.apply(500)
      node = gui.subScene
    }.play()
  }

  override def playBefore(gui: Gui3d): Unit = {
    fadeOut(gui)
  }
  def fadeOut(gui: Gui3d): Unit = {
    new FadeTransition {
      duration = Duration.apply(500)
      fromValue = 1.0
      toValue = 0.01
      node = gui.subScene
    }.play()
  }
}
