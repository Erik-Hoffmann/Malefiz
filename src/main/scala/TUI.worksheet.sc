
val players = 5
val width = players*3+2
val eol: String = sys.props("line.separator")

def linSpace(lower: Int, upper: Int, num: Int): List[Int] =
    if (num == 1) then List(upper/2)
    else if (num == 2) then List(lower, upper-1)
    else lower.to(upper, (upper-lower)/num+1).toList

def vertBar(innerWidth: Int, vertices: Int): String =
    "   " * ((width-innerWidth)/2) +
      0.to(width).map(
          i => if(linSpace(0, innerWidth, vertices).contains(i)) then " □ " else "   "
      ).mkString("")
      + "   " * ((width-innerWidth)/2) + eol

def bar(innerWidth: Int): String = "   " * ((width-innerWidth)/2) + " □ " * innerWidth + "   " * ((width-innerWidth)/2) + eol

def mesh(): String =
    vertBar(1,1) +
    1.until(players).map(
        i => (bar(i*4+1) + vertBar(i*4+1,i+1))
    ).mkString("")
      + bar(width)

print(mesh())