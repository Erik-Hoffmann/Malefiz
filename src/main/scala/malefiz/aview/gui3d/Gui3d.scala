package malefiz
package aview.gui3d

import scalafx.scene.input.KeyEvent
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.BackgroundImage
import scalafx.scene.image
import scalafx.animation.FadeTransition
import animationStates.{AnimationState, MoveState, SetState, StartState}
import controller.{Controller, ControllerInterface}
import malefiz.aview.ControlSubScene
import util.Observer
import  controller.State
import model.{Colors, EmptyGround, Field, GameBoard }
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
  var subScene: SubScene = null
  var gui = this
  con.add(this)
  con.dice()

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
          subScene = getBoard3d()
          content = new VBox(subScene,new ControlSubScene(con, new HBox,VIEWPORT_SIZE, gui))
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
        subScene = getBoard3d()
        content = new VBox(subScene, new ControlSubScene(con, new HBox,VIEWPORT_SIZE, gui))
        cameraBody.getTransforms().add(new Rotate(30, Rotate.XAxis))
      }
    }
  }
  def getBoard3d(): SubScene = {
    val group = new Group
    val subScene = new SubScene(group ,VIEWPORT_SIZE, VIEWPORT_SIZE * 9.0 / 16
      , true, SceneAntialiasing.Balanced)
    val gameboard = con.getBoard
    setupImageView()
    calculatePossibleMoves()
    group.getChildren.add(imgView)
    for (i <- 0 until gameboard.height) {
      for (j <- 0 until gameboard.width) {
        println(i+":"+j)
        if (!gameboard.board(i)(j).isFree) {
          if (isPeg(i,j)){
            group.getChildren.add(new BoardCylinder(i, j) {
              color = DarkGray
              translateX = i * boxSize
              translateY = j * boxSize
              checkIfinAPossibleMove((i, j), this)
            })
            group.getChildren.add(new Peg3d(i, j) {
              x = i * boxSize
              y = j * boxSize
              z = 0
              this.onMouseClicked = handle {
                if (!con.currentPlayer.getPegByPos(posy, posx).isEmpty) {
                  if (!choosenPeg.isEmpty) {
                    choosenPeg.get.color = mapColor(con.currentPlayer.color)
                  }
                  choosenPeg = Option.apply(this)
                  choosenPeg.get.color = Color.DeepPink
                  textContainer.text = "Move Peg to Position"
                  con.inputExecute(posx,posy)
                }
              }
              if (!choosenPeg.isEmpty && choosenPeg.get.posx == i && choosenPeg.get.posy == j) {
                color = Color.DeepPink
                moved = true
              } else {
                for (p <- con.getBoard.players) {
                  if (!p.getPegByPos(posy, posx).isEmpty) {
                    color = mapColor(p.color);
                  }
                }
              }
            })
          } else {
            group.getChildren.add(new Box {
              translateX = i * boxSize
              translateY = j * boxSize
              width = boxSize
              height = boxSize
              depth = 0
              scaleX = SCALE
              scaleY = SCALE
              scaleX = SCALE
              material = new PhongMaterial(Transparent)
            })
          }
        } else {
          group.getChildren.add(new BoardCylinder(i, j) {
            color = DarkGray
            translateX = i * boxSize
            translateY = j * boxSize
            checkIfinAPossibleMove((i, j), this)
          })
          if (gameboard.board(i)(j).asInstanceOf[Field].isBlocker) {
            group.getChildren.add(new BoardCylinder(i, j) {
              color = Black
              height = 40
              translateX = i * boxSize
              translateY = j * boxSize
              translateZ = boxDepth + boxDepth
            })
          }
        }
      }
    }
    setUpCamera()
    subScene.camera = cameraBody
    subScene.fill = Beige
    showPossibleMoves()
    subScene
  }
  def showPossibleMoves(): Unit = {
    if (choosenPeg.isEmpty) {
    } else {
      for (pm <- possibleMoves) {
        pm.checkPegCanWalk(choosenPeg.get.posx, choosenPeg.get.posy)
      }
    }
  }
  def setUpCamera(): Unit = {
    cameraBody.scaleX = SCALE
    cameraBody.scaleY = SCALE
    cameraBody.scaleZ = SCALE
    cameraBody.translateZ = con.getBoard.players.length*30
    cameraBody.translateX = -((VIEWPORT_SIZE * 9.0 / 16) - con.getBoard.height*40)/2
    cameraBody.translateY = -20 -(VIEWPORT_SIZE - con.getBoard.width*40)/2
    if(con.getBoard.players.length >= 6) {
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
    imgView.fitWidth = con.getBoard.width*boxSize + 50
    imgView.fitHeight = con.getBoard.height*boxSize + 50
    imgView.translateY =  -65 + (con.getBoard.players.length * 40)
    imgView.translateX = -20 -(con.getBoard.players.length * 40)
  }
  def isPeg(x: Int, y:Int): Boolean = {
    for (p <- con.getBoard.players) {
      if(!p.getPegByPos(y,x).isEmpty){
        return true
      }
    }
    false
  }
  def calculatePossibleMoves(): Unit = {
    for (peg <- con.currentPlayer.pegs) {
      if (peg == null) {
      } else {
        possibleMoves = possibleMoves :+ new PossibleMoves((peg.y, peg.x))
        calculateMove((peg.y, peg.x), con.diced, Direction.Down)
      }
    }
  }
  def calculateMove(pos : (Int,Int), moves: Int, directionBefore: Direction): Unit = {
    if (pos._1 - 1 <= con.getBoard.height && pos._2 - 1 >= 0 && pos._2 + 1 < con.getBoard.width ) {
      if(checkField((pos._1-1, pos._2), moves)) {
        calculateMove((pos._1 - 1, pos._2), moves - 1, Direction.Up)
      }
      if (checkField((pos._1, pos._2 + 1), moves) && directionBefore != Direction.Left){
        calculateMove((pos._1, pos._2 + 1), moves - 1, Direction.Right)
      }
      if (checkField((pos._1, pos._2 - 1), moves)&& directionBefore != Direction.Right) {
        calculateMove((pos._1, pos._2 - 1), moves - 1, Direction.Left)
      }
      if (moves == 0) {
        possibleMoves.last.addPos(pos)
      }
    }
  }
  def checkField(pos: (Int, Int), moves: Int): Boolean = {
    if (!con.getBoard.board(pos._1)(pos._2).isFree) {
      false
    } else {
      if (!(con.getBoard.board(pos._1)(pos._2).asInstanceOf[Field].isBlocker)
        && moves > 0) {
        return true
      } else if (con.getBoard.board(pos._1)(pos._2).asInstanceOf[Field].isBlocker
        && moves == 1){
        return true
      }
      false
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
    radius = boxSize / 2
    height = boxDepth
    rotationAxis = Rotate.XAxis
    rotate = 90
    onMouseClicked = handle {
      con.state match
        case State.ChooseDest =>
          guiState = new MoveState()
          guiState.playBefore(gui)
          con.inputExecute(x,y)
        case State.ChooseBlockerTarget =>
          con.inputExecute(x,y)
        case _ =>
    }
    var selfColor = DarkGray

    def color: Color = Beige

    def color_=(c: Color): Unit = {
      material = new PhongMaterial(diffuseColor = c)
      selfColor = c
    }
  }

  class PossibleMoves(pegPos: (Int, Int)) {
    var possibleMoves = new Array[(Int, Int)](0)
    var walkableFields = new Array[BoardCylinder](0)

    def checkPeg(pos: (Int, Int)): Boolean = {
      pegPos.equals(pos)
    }

    def checkPegCanWalk(pos: (Int, Int)): Unit = {
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

    def addPos(pos: (Int, Int)): Unit = {
      possibleMoves = possibleMoves :+ pos
    }
  }
}
object Gui3d {
  @main def main(): Unit = {
    val con = new Controller(4)
    val gui = new Gui3d(con)
    val threadGui = new Thread {
      override def run(): Unit = {
        gui.main(Array[String]())
      }
    }
    threadGui.start()
  }
}
