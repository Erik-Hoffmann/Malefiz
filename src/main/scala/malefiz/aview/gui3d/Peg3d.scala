package malefiz.aview.gui3d


import javafx.fxml.FXMLLoader
import scalafx.geometry.Point3D
import scalafx.scene.paint.Color.{Beige, Red}
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.shape.{DrawMode, MeshView, TriangleMesh}

class Peg3d extends MeshView(new TriangleMesh(delegate = FXMLLoader.load(getClass.getResource("/Models/pegHD.fxml")))) {
  scaleX = 20
  scaleY = 20
  scaleZ = 20
  drawMode = DrawMode.Fill
  def color: Color = Red
  def color_=(c : Color): Unit = {
    material = new PhongMaterial( diffuseColor = c)
  }
  def z : Double = 0
  def z_=(d :Double) : Unit = {
    translateZ = d + 20
  }
  rotationAxis = Point3D(1,0,0)
  rotate = 270
}
