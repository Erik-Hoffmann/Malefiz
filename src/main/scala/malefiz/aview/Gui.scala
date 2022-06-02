package malefiz.aview

import scalafx.scene.Scene

trait Gui {
  def start: Scene
  def update: Scene
}
