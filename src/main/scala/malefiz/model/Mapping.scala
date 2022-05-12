//package malefiz
//package model
//
//
//case class Mapping(players: Int):
//  this(players: Int) = this(List(target(players)) ::: blockLayers(players))
//  def target(players: Int): List[Int] = List(borderSpace(players*4+1, 1)+1)
//  def blockLayers(players: Int): List[List[Int]] = ( for (i <- players - 1 until 0 by -1)
//    yield blockLayer(players*4+1, players*4+1 - (i * 4), players - i + 1) ).flatten.toList
//  def blockLayer(width: Int, num: Int, v: Int): List[List[Int]] = List(( for (i <- 0 until num) yield i + borderSpace(width, num)).toList, linSpace(0, num, v))
//  def linSpace(a: Int, b: Int, num: Int): List[Int] =
//    if num == 2 then List(a, b - 1)
//    else a.to(b, (b - a) / num + 1).toList
//  def borderSpace(width: Int, num: Int): Int = ((width - num) / 2)