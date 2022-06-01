package malefiz.model.BaseImpl

enum Colors(display: String):
  override def toString: String = display
  case red extends Colors("\\u001b[31m")
  case green extends Colors("\\u001b[32m")
  case yellow extends Colors("\\u001b[33m")
  case blue extends Colors("\\u001b[34m")
  case magenta extends Colors("\\u001b[35m")
  case cyan extends Colors("\\u001b[36m")
  case default extends Colors("\\u001b[0m")
end Colors