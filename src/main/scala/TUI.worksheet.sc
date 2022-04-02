
// return list of equally spaced indices if num bigger 2
def linspace(a: Int, b: Int, num: I: List[Int] =
  if (num == 2) then List(a, b-1)
  else a.to(b, (b-a)/num+1).toList

val num = 13
println(linspace(0, 12, 4))
println(linspace(0, 13, 4))
println(linspace(0, 5, 2))

val space = "   "
val field = " X "
val project = linspace(0, 5, 2)

println(project)

// _____________
// X   X   X   X
// 0___4___8___12
def listProjector(l: List[Int]) =
    0.to(l.last).map(x => if (l.contains(x)) then field else space).mkString("")

println(listProjector(project))