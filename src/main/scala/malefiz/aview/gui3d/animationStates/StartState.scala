package malefiz.aview.gui3d.animationStates

import malefiz.aview.gui3d.Gui3d
import scalafx.animation.RotateTransition
import scalafx.scene.transform.Rotate
import scalafx.util.Duration

class StartState  extends AnimationState{
  override def playBefore(gui :Gui3d): Unit = {}
  override def playAfter(gui: Gui3d): Unit = {
    rotateCamera(gui)
    gui.cameraBody.getTransforms().add(new Rotate(330, Rotate.XAxis))
    gui.textContainer.text = "set new Peg"
    gui.guiState = new SetState
  }
  def rotateCamera(gui: Gui3d): Unit = {
    new RotateTransition {
    duration = Duration.apply(1500)
    axis = Rotate.YAxis
    node = gui.cameraBody
    byAngle = 30
    }.play()
  }

}
