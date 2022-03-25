val eol = System.getProperty("line.separator")

val players = 4
val width = 17
val space = " "
val playableField = "o"
val baseLine = playableField * width

def l1Box() = (baseLine + eol + (playableField + space * 3) * 4 + playableField + eol + baseLine)

def l2Box() = space * ((width-13) / 2) + playableField * 13 + space * ((width-13) / 2) + eol+
  space * ((width-13) / 2)+ (playableField + (space * 3) * 3) + playableField + space * ((width-13) / 2)

def l3Box() = space * ((width - 9 ) / 2) + playableField * 9 + space * ((width - 9 ) / 2) + eol +
  space * ((width - 9 ) / 2) + playableField + space * 7 + playableField + space * ((width - 9 ) / 2)

def l4Box() = space * (width / 2) + playableField + space * (width / 2) + eol +
  space * ((width - 5) / 2) + (playableField * 5) + space * ((width - 5) / 2) + eol +
  space * ((width - 5) / 2) + playableField + space*3 + playableField + space * ((width - 5) / 2)

def l5Box() = (baseLine + eol + playableField + space * (width-2) + playableField + eol+ baseLine)

def field() = (l5Box() + eol + l4Box() + eol + l3Box() + eol + l2Box() + eol + l1Box())

@main def main: Unit =
  println(field())