import scala.collection.mutable.ListBuffer

// return list of equally spaced indices if num bigger 2
val eol = System.getProperty("line.separator")
val field = "1"
val space = "0"
val stone = " ■ " // for later maybe

def linspace(a: Int, b: Int, num: Int): List[Int] =
    if (num == 2) then List(a, b-1)
    else a.to(b, (b-a)/num+1).toList

def listProjector(l: List[Int]): ListBuffer[Int] = {
    var list = new ListBuffer[Int]()
    for (i <- 0 until l.last + 1) {
        if (l.contains(i)) {
            list += 1
        } else {
            list += 0
        }
    }
    return list;
}

def borderSpace(width: Int, num: Int): ListBuffer[Int] = {
    val list = new ListBuffer[Int]();
    for(i <- 0 until ((width - num) / 2)){
         list += 0;
    }
    return list;
}

def centerPath(width: Int, num: Int): ListBuffer[Int] = {
    var list = borderSpace(width, num)
    for (i <- 0 until num) {
        list += 1
    }
    list ++= borderSpace(width, num)
}

def vPath(width: Int, num: Int, v: Int): ListBuffer[Int] =
    borderSpace(width, num) ++= listProjector(linspace(0, num, v)) ++= borderSpace(width, num)

def blockLayer(width:Int, num: Int, v: Int) =
    centerPath(width, num) ++= vPath(width, num, v)


def buildPyramid(width: Int, players: Int) = {
    val builder = new ListBuffer[Int]();
    builder ++= borderSpace(width, 1) ++= List(1) ++= borderSpace(width, 1)
    val innerWidth = (width-5).to(0).by(-4).toList
    for {i <- players-1 until 0 by -1} {
        builder ++= blockLayer(width, width - (i*4), players-i+1)
    }
    builder ++= blockLayer(width, width, players + 1) ++= centerPath(width, width)
}

var player = 4;
var width = player * 4 + 1
listProjector(List(0, 4, 8))
listProjectorS(List(0, 4, 8))

var list = buildPyramid(width, player)
for (i <- 1 until list.length + 1) {
    print(list(i - 1))
    if (i % width == 0) {
        print(eol)
    }
}

val num = 13

println(linspace(0, 12, 4))
println(linspace(0, 13, 4))
println(linspace(0, 5, 2))

val project = linspace(0, 2, 5)

println(project)

// __X__
// __XX__

// _____________
// X   X   X   X
// 0___4___8___12

println(listProjector(project))