package malefiz.model

enum Place(display: String):
  override def toString: String = display
  case stone extends Place(" ■ ")
  case player extends Place(" \uE008 ")
  case field extends Place(" □ ")
  case space extends Place("   ")
end Place

class Token(place: String, theColor: String = "default"):
  override def toString: String = Place.valueOf(place).toString + Colors.valueOf(theColor).toString