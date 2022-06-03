package malefiz.aview.gui3d

import scalafx.animation.FadeTransition
import malefiz.aview.Gui
import malefiz.controller.BaseImpl.Turn
import malefiz.controller.ControllerInterface
import scalafx.Includes.*
import scalafx.animation.{Animation, RotateTransition, Transition}
import scalafx.application.JFXApp3
import scalafx.beans.binding.Bindings
import scalafx.event.EventIncludes.handle
import scalafx.scene.control.{Button, CheckBox}
import scalafx.scene.layout.{BorderPane, GridPane, HBox, Priority, VBox}
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.paint.Color.{Beige, Black, Blue, Cyan, Red, White}
import scalafx.scene.shape.*
import scalafx.scene.*
import scalafx.scene.input.{KeyCode, ScrollEvent}
import scalafx.scene.transform.Rotate
import scalafx.stage.Stage
import scalafx.util.Duration

class Gui3d(con : ControllerInterface) extends Gui {
  val VIEWPORT_SIZE = 800
  val SCALE = 1
  val boxWidth = 40
  val boxLength = 40
  val boxDepth = 10
  var cameraBody = new PerspectiveCamera()
  var GuiState = new StateClass

  override def start: Scene = {
    new Scene {
      fill = Black
      val subScene = getBoard3d()
      GuiState.state = State.choose
      content = new VBox(subScene, new ControlSubScene(con, new HBox,VIEWPORT_SIZE, fadeOut(subScene), GuiState))
      val transition = new RotateTransition {
        duration = Duration.apply(1500)
        axis = Rotate.YAxis
        node = cameraBody
        byAngle = 30
      }
      transition.play()
    }
  }
  override def update: Scene = {
    cameraBody = new PerspectiveCamera()
    new Scene {
      fill = Black
      val subScene = getBoard3d()
      val transition = fadeIn(subScene)
      if (GuiState.state == State.undo || GuiState.state == State.redo){
        transition.play()
      }
      GuiState.state = State.choose
      content = new VBox(subScene, new ControlSubScene(con, new HBox,VIEWPORT_SIZE, fadeOut(subScene), GuiState))
      cameraBody.getTransforms().add(new Rotate(30, Rotate.XAxis))
    }
  }
  def getBoard3d(): SubScene = {
    val group = new Group
    val subScene = new SubScene(group ,VIEWPORT_SIZE, VIEWPORT_SIZE * 9.0 / 16
      , true, SceneAntialiasing.Balanced)
    for (i <- 0 until con.field.height) {
      for (j <- 0 until con.field.width) {
        if (con.field.checkEmpty(i, j)) {
          group.getChildren.add(new BoardBox(i, j) {
            color = White
            translateX = i * boxWidth
            translateY = j * boxLength
          })
        } else if (con.field.checkFreeField(i, j)) {
          group.getChildren.add(new BoardBox(i, j) {
            color = Blue
            translateX = i * boxWidth
            translateY = j * boxLength
          })
        } else if (con.field.checkBlocker(i, j)) {
          group.getChildren.add(new BoardBox(i, j) {
            color = Blue
            translateX = i * boxWidth
            translateY = j * boxLength
          })
          group.getChildren.add(new BoardBox(i, j) {
            color = Black
            depth = 40
            translateX = i * boxWidth
            translateY = j * boxLength
            translateZ = boxDepth + boxDepth
          })
        } else if (con.field.checkPeg(i, j)) {
          group.getChildren.add(new BoardBox(i, j) {
            color = Blue
            translateX = i * boxWidth
            translateY = j * boxLength
          })
          group.getChildren.add(new Peg3d {
            translateX = i * boxWidth
            translateY = j * boxLength
            z = boxDepth + boxDepth
            color = Red
          })
        }
      }
    }
    setUpCamera()
    subScene.camera = cameraBody
    subScene.fill = Cyan
    subScene
  }
  def setUpCamera(): Unit = {
    cameraBody.scaleX = SCALE
    cameraBody.scaleY = SCALE
    cameraBody.scaleZ = SCALE
    cameraBody.translateZ = con.field.playerList.length*30
    cameraBody.translateX = -((VIEWPORT_SIZE * 9.0 / 16) - con.field.height*40)/2
    cameraBody.translateY = -(VIEWPORT_SIZE - con.field.width*40)/2
    cameraBody.getTransforms().add(new Rotate(180, Rotate.YAxis))
    cameraBody.getTransforms().add(new Rotate(90, Rotate.ZAxis))
  }
  def fadeOut(n :Node): FadeTransition = {
    new FadeTransition {
      duration = Duration.apply(500)
      fromValue = 1.0
      toValue = 0.01
      node = n
    }
  }
  def fadeIn(n :Node): FadeTransition = {
    new FadeTransition {
      fromValue = 0.01
      toValue = 1.0
      duration = Duration.apply(500)
      node = n
    }
  }
  class BoardBox(x: Int, y: Int) extends Box {
    width = boxWidth
    height = boxLength
    depth = boxDepth
    scaleX = SCALE
    scaleY = SCALE
    scaleX = SCALE
    onMouseClicked = handle {
      con.put(new Turn(Option.empty, (x, y)))
    }
    def color: Color = Beige
    def color_=(c: Color): Unit = {
      material = new PhongMaterial(diffuseColor = c)
    }
  }
}
