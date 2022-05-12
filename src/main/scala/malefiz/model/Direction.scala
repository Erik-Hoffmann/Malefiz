package malefiz.model

enum Direction(display: String):
  override def toString: String = display
  case Left extends Direction("l")
  case Right extends Direction("r")
  case Up extends Direction("u")
  case Down extends Direction("d")
  case Empty extends Direction("")
end Direction
