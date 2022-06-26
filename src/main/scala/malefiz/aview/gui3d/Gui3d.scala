package malefiz
package aview.gui3d

import scalafx.scene.input.KeyEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.BackgroundImage
import scalafx.scene.image
import scalafx.animation.FadeTransition
import animationStates.{AnimationState, MoveState, SetState, StartState}
import controller.BaseImpl.{Controller, Turn}
import controller.ControllerInterface
import util.Observer
import model.BaseImpl.{Colors, Direction, Gameboard}
import scalafx.Includes.*
import scalafx.animation.{Animation, RotateTransition, Transition}
import scalafx.application.{JFXApp3, Platform}
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


class Gui3d(con : ControllerInterface) extends JFXApp3, Observer{
  val VIEWPORT_SIZE = 800
  val SCALE = 1
  val boxSize = 40
  val boxDepth = 30
  var choosenPeg :Option[Peg3d] = None
  var cameraBody = new PerspectiveCamera()
  var imgView = new ImageView()
  var guiState: AnimationState = new StartState()
  var moved = false
  var possibleMoves = new Array[PossibleMoves](0)
  var textContainer: TextField = null
  var gui = this
  con.add(this)

  override def start(): Unit = {
      stage = new JFXApp3.PrimaryStage {
        title = "Malefiz"
        imgView = new ImageView(getClass.getResource("/background-old-paper.jpg").toString)
        textContainer = new TextField {
          text = "choose direction"
        }
        scene = new Scene {
          cameraBody = new PerspectiveCamera()
          fill = Black
          guiState.playAfter(gui)
          content = new VBox(getBoard3d(),new ControlSubScene(con, new HBox,VIEWPORT_SIZE, gui))
          cameraBody.getTransforms().add(new Rotate(30, Rotate.XAxis))
        }
      }
    }

  override def update(): Unit = {
    Platform.runLater {
      cameraBody = new PerspectiveCamera()
      stage.scene = new Scene {
        fill = Black
        guiState.playAfter(gui)
        content = new VBox(getBoard3d(), new ControlSubScene(con, new HBox,VIEWPORT_SIZE, gui))
        cameraBody.getTransforms().add(new Rotate(30, Rotate.XAxis))
      }
    }
  }
  def getBoard3d(): SubScene = {
    val group = new Group
    val subScene = new SubScene(group ,VIEWPORT_SIZE, VIEWPORT_SIZE * 9.0 / 16
      , true, SceneAntialiasing.Balanced)
    setupImageView()
    calculatePossibleMoves()
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
            checkIfinAPossibleMove((i,j), this)
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
            checkIfinAPossibleMove((i,j), this)
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
                for (pm <- possibleMoves) {
                  pm.returnFieldColor()
                  pm.checkPegCanWalk((i,j))
                }
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

  def calculatePossibleMoves(): Unit = {
    if (con.field.currentPlayer.pegs.isEmpty) {
      return;
    }
    for (peg <- con.field.currentPlayer.pegs) {
      possibleMoves = possibleMoves :+ new PossibleMoves(peg)
      calculateMove(peg, con.diced, Direction.Down)
    }
  }
  def calculateMove(pos : (Int,Int), moves: Int, directionBefore: Direction): Unit = {
    if (pos._1 - 1 <= con.field.height && pos._2 - 1 >= 0 && pos._2 + 1 < con.field.width ) {
      if ((con.field.checkFreeField(pos._1 - 1, pos._2)
        || con.field.checkPeg(pos._1 - 1, pos._2))
        && moves > 0) { // check up
        calculateMove((pos._1 - 1, pos._2), moves - 1, Direction.Up)
      }
      if ((con.field.checkFreeField(pos._1, pos._2 - 1)
        || con.field.checkPeg(pos._1, pos._2 - 1))
        && moves > 0
        && directionBefore != Direction.Left) { // check right
        calculateMove((pos._1, pos._2 - 1), moves - 1, Direction.Right)
      }
      if ((con.field.checkFreeField(pos._1, pos._2 + 1)
        || con.field.checkPeg(pos._1, pos._2 + 1))
        && moves > 0
        && directionBefore != Direction.Right) { // check left
        calculateMove((pos._1, pos._2 + 1), moves - 1, Direction.Left)
      }
      if (moves == 0) {
        possibleMoves.last.addPos(pos)
      }
    }
  }

  def checkIfinAPossibleMove(pos: (Int,Int), cylinder: BoardCylinder): Unit = {
    if(possibleMoves == null){
      return
    }
    for (pm <- possibleMoves) {
      if (pm.possibleMoves.contains(pos)) {
        pm.addField(cylinder)
      }
    }
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
      if (selfColor == Beige) {
        val turn = new Turn(Option.apply(choosenPeg.get.posx, choosenPeg.get.posy), (x,y))
        guiState = new MoveState()
        guiState.playBefore(gui)
        con.diced = 0
        con.put(turn)
      }
      guiState = new SetState
      guiState.playBefore(gui)
      con.put(new Turn(Option.empty, (x, y)))
    }
    var selfColor = DarkGray
    def color: Color = Beige
    def color_=(c: Color): Unit = {
      material = new PhongMaterial(diffuseColor = c)
      selfColor = c
    }
  }
  class PossibleMoves(pegPos: (Int,Int)) {
    var possibleMoves = new Array[(Int,Int)](0)
    var walkableFields = new Array[BoardCylinder](0)

    def checkPeg(pos: (Int,Int)): Boolean = {
      pegPos.equals(pos)
    }

    def checkPegCanWalk(pos: (Int,Int)): Unit = {
      if (pos.equals(pegPos)) {
        for (field <- walkableFields) {
          field.color = Color.Beige
        }
      }
    }
    def returnFieldColor(): Unit = {
      for (field <- walkableFields) {
        field.color = Color.DarkGray
      }
    }
    def addField(field: BoardCylinder): Unit = {
      walkableFields = walkableFields :+ field
    }
    def addPos(pos :(Int,Int)): Unit = {
      possibleMoves = possibleMoves :+ pos
    }
  }
}
object Gui3d {
  @main def main(): Unit = {
    val gb = new Gameboard(4)
    val con = new Controller(gb)
    val gui = new Gui3d(con)
    val threadGui = new Thread {
      override def run(): Unit = {
        gui.main(Array[String]())
      }
    }
    threadGui.start()
  }
}
