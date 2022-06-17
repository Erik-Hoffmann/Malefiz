package malefiz
package aview.gui3d


import javafx.fxml.FXMLLoader
import scalafx.scene.shape.CullFace
import scalafx.geometry.Point3D
import scalafx.scene.paint.Color.{Beige, Red}
import scalafx.scene.paint.{Color, PhongMaterial}
import scalafx.scene.shape.{DrawMode, MeshView, TriangleMesh}
import scalafx.scene.transform.Rotate

class Peg3d(posX: Int, posY: Int) extends MeshView(new TriangleMesh(delegate = FXMLLoader.load(getClass.getResource("/Models/CowboyPeg.fxml")))) {
  scaleX = 19
  scaleY = 19
  scaleZ = 19
  var posx: Int = posX
  var posy: Int = posY
  cullFace = CullFace.None
  drawMode = DrawMode.Fill
  def color: Color = Red
  def color_=(c : Color): Unit = {
    material = new PhongMaterial( diffuseColor = c)
  }
  def x : Double = 0
  def x_=(d: Double): Unit = {
    translateX = d
  }
  def z : Double = 0
  def z_=(d :Double) : Unit = {
    translateZ = d + 20
  }
  def y : Double = 0
  def y_=(d :Double): Unit = {
    translateY = d -33
  }
  this.getTransforms.add(new Rotate(270, Rotate.XAxis))
  this.getTransforms.add(new Rotate(270, Rotate.YAxis))
}
