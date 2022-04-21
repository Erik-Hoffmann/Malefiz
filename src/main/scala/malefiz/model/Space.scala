package malefiz.model

class Space(val field: Int) {
  override def toString: String = {
    field match {
      case 0 => return "   ";
      case 1 => return " □ ";
      case 2 => return " ■ ";
    }
  }
}

  
