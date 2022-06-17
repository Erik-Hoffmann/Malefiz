package malefiz
package aview.gui3d

import scalafx.scene.input.KeyEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.BackgroundImage
import scalafx.scene.image
import scalafx.animation.FadeTransition
import aview.Gui
import aview.gui3d.animationStates.{AnimationState, SetState, StartState}
import controller.BaseImpl.Turn
import controller.ControllerInterface
import model.BaseImpl.Colors
import scalafx.Includes.*
import scalafx.animation.{Animation, RotateTransition, Transition}
import scalafx.application.JFXApp3
import scalafx.beans.binding.Bindings
import scalafx.event.EventIncludes.handle
import scalafx.geometry.Point3D
import scalafx.scene.control.{Button, CheckBox, TextField}
import scalafx.scene.layout.{BorderPane, GridPane, HBox, Priority, VBox}
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.paint.Color.{Beige, Black, Blue, Cyan, DarkGray, Green, Magenta, Red, Transparent, White, Yellow}
import scalafx.scene.shape.*
import scalafx.scene.*
import scalafx.scene.effect.{DropShadow, Glow}
import scalafx.scene.input.{KeyCode, ScrollEvent}
import scalafx.scene.transform.Rotate
import scalafx.stage.Stage
import scalafx.util.Duration


class Gui3d(con : ControllerInterface) extends Gui {
  val VIEWPORT_SIZE = 800
  val SCALE = 1
  val boxSize = 40
  val boxDepth = 30
  var choosenPeg :Option[Peg3d] = None
  var cameraBody = new PerspectiveCamera()
  val imgView = new ImageView(getClass.getResource("/background-old-paper.jpg").toString)
  var guiState: AnimationState = new StartState()
  var subScene = getBoard3d()
  var moved = false
  val textContainer = new TextField {
    text = "choose direction"
  }
  var gui = this

  override def update: Scene = {
    cameraBody = new PerspectiveCamera()
    new Scene {
      fill = Black
      subScene = getBoard3d()
      guiState.playAfter(gui)
      content = new VBox(subScene, new ControlSubScene(con, new HBox,VIEWPORT_SIZE, gui))
      cameraBody.getTransforms().add(new Rotate(30, Rotate.XAxis))
    }
  }
  def getBoard3d(): SubScene = {
    val group = new Group
    val subScene = new SubScene(group ,VIEWPORT_SIZE, VIEWPORT_SIZE * 9.0 / 16
      , true, SceneAntialiasing.Balanced)
    setupImageView()
    group.getChildren.add(imgView)
    for (i <- 0 until con.field.height) {
      for (j <- 0 until con.field.width) {
        if (con.field.checkEmpty(i, j)) {
          group.getChildren.add(new Box {
            translateX = i * boxSize
            translateY = j *boxSize
            width = boxSize
            height =boxSize
            depth = 0
            scaleX = SCALE
            scaleY = SCALE
            scaleX = SCALE
            material = new PhongMaterial(Transparent)
          })
        } else if (con.field.checkFreeField(i, j)) {
          group.getChildren.add(new BoardCylinder(i, j) {
            color = DarkGray
            translateX = i * boxSize
            translateY = j * boxSize
          })
        } else if (con.field.checkBlocker(i, j)) {
          group.getChildren.add(new BoardCylinder(i, j) {
            color = DarkGray
            translateX = i * boxSize
            translateY = j * boxSize
          })
          group.getChildren.add(new BoardCylinder(i, j) {
            color = Black
            height = 40
            translateX = i * boxSize
            translateY = j * boxSize
            translateZ = boxDepth + boxDepth
          })
        } else if (con.field.checkPeg(i, j)) {
          group.getChildren.add(new BoardCylinder(i, j) {
            color = DarkGray
            translateX = i * boxSize
            translateY = j * boxSize
          })
          group.getChildren.add(new Peg3d(i,j) {
            x = i * boxSize
            y = j * boxSize
            z = 0
            this.onMouseClicked =  handle {
              if (con.field.currentPlayer.pegs.contains((i, j))) {
                if (!choosenPeg.isEmpty) {
                  choosenPeg.get.color = mapColor(con.field.currentPlayer.colour)
                }
                choosenPeg = Option.apply(this)
                choosenPeg.get.color = Color.DeepPink
                textContainer.text = "Move Peg to Position"
              }
            }
            if(!choosenPeg.isEmpty && choosenPeg.get.posx == i && choosenPeg.get.posy == j ){
              color = Color.DeepPink
              moved = true
            } else {
              for (p <- con.field.playerList) {
                if(p.pegs.contains(i,j)){
                  color = mapColor(p.colour);
                }
              }
            }
          })
        }
      }
    }
    setUpCamera()
    subScene.camera = cameraBody
    subScene.fill = Beige
    subScene
  }
  def setUpCamera(): Unit = {
    cameraBody.scaleX = SCALE
    cameraBody.scaleY = SCALE
    cameraBody.scaleZ = SCALE
    cameraBody.translateZ = con.field.playerList.length*30
    cameraBody.translateX = -((VIEWPORT_SIZE * 9.0 / 16) - con.field.height*40)/2
    cameraBody.translateY = -20 -(VIEWPORT_SIZE - con.field.width*40)/2
    if(con.field.playerList.length >= 6) {
      cameraBody.translateX = cameraBody.translateX.value + 150
    }
    cameraBody.getTransforms().add(new Rotate(180, Rotate.YAxis))
    cameraBody.getTransforms().add(new Rotate(90, Rotate.ZAxis))
  }
  def setupImageView(): Unit = {
    imgView.scaleY = SCALE
    imgView.scaleX = SCALE
    imgView.scaleZ = SCALE
    imgView.setPreserveRatio(false)
    imgView.rotationAxis = Rotate.ZAxis
    imgView.rotate = 90
    imgView.fitWidth = con.field.width*boxSize + 50
    imgView.fitHeight = con.field.height*boxSize + 50
    imgView.translateY =  -65 + (con.field.playerList.length * 40)
    imgView.translateX = -20 -(con.field.playerList.length * 40)
  }

  def mapColor(c: Colors): Color = {
    c match {
      case Colors.red => Red
      case Colors.blue => Blue
      case Colors.magenta =>Magenta
      case Colors.cyan => Cyan
      case Colors.green =>Green
      case Colors.yellow => Yellow
      case Colors.default => Black
    }
  }

  class BoardCylinder(x: Int, y: Int) extends Cylinder {
    scaleX = SCALE
    scaleY = SCALE
    scaleX = SCALE
    radius = boxSize/2
    height = boxDepth
    rotationAxis = Rotate.XAxis
    rotate = 90
    onMouseClicked = handle {
      guiState = new SetState
      guiState.playBefore(gui)
      con.put(new Turn(Option.empty, (x, y)))
    }
    def color: Color = Beige
    def color_=(c: Color): Unit = {
      material = new PhongMaterial(diffuseColor = c)
    }
  }
}
