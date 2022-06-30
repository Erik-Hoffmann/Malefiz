
import malefiz.aview.TUI
import malefiz.aview.gui3d.Gui3d
import malefiz.controller.Controller
import malefiz.model.fileIO.serializeJSON.serialize
import play.api.libs.json.{JsArray, JsNumber, Json}

val con = new Controller(4)
val player = con.currentPlayer
var json = new JsArray()
for (pegs <- player.pegs) {
  if(pegs == null) {}
  else
    json = json :+ Json.obj(
        "x" -> JsNumber(pegs.x),
        "y" -> JsNumber(pegs.y)
      )
}
json.toString


