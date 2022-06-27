package malefiz
package model.fileIO

import controller.ControllerInterface

trait fileIOInterface:
  val baseName: String = "save"
  val extension: String = ""
  val fileName: String = baseName + extension
  def dumps(controller: ControllerInterface): Unit
  def loads(controller: ControllerInterface): Unit