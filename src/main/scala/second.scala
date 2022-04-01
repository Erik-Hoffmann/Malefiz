import scala.io.StdIn.readLine

val eol = System.getProperty("line.separator")
val field = " X "
val space = "   "

def buildLayer(players: Int, size: Int) =
  val width = (players*players+1)
  field * width + eol +
    ((field + (space*(width-2)) + field) + eol) * size +
    field * width + eol

def sField() =
  val players = readLine("Number of players: ").toInt
  val layers = readLine("Layers: ").toInt
  val layerSize = readLine("Layerheight: ").toInt
  buildLayer(players, layerSize)*layers

@main def sMain: Unit =
  println(sField())