package malefiz.model

enum Tokens(display: String):
  override def toString: String = display
  case stone extends Tokens(" ■ ")
  case player extends Tokens(" \uE008 ")
  case field extends Tokens(" □ ")
  case space extends Tokens("   ")
