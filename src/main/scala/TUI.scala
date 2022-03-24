class TUI {
  //0 = empty 1 = path 2 = stone 3 = end
  var board = Array(
    0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,
    1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,
    1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
    1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,
    0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,
    0,0,0,0,0,0,1,1,2,1,1,0,0,0,0,0,0,
    0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,
    0,0,0,0,1,1,2,1,1,1,2,1,1,0,0,0,0,
    0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,
    0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,
    0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,
    2,1,1,1,2,1,1,1,2,1,1,1,2,1,1,1,2,
    1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,
    1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
  );
  //0 = empty
  //1 = path
  //2 = stone
  //3 = end

  private val SPACE = " ";
  private val PATH = "o"
  private val STONE = "I"
  private val END = "W"




  def decodeBoard(symbol: Int):String = {
    symbol match {
      case 0 => SPACE;
      case 1 => PATH;
      case 2 => STONE;
      case 3 => END;
    }
  }

  def presentBoard(): String = {
    var builder = new StringBuilder();
    for(i <- 0 until board.length) {
      if (i % 17 == 0 && i != 0) {
        builder.append("\n")
      }
      builder.append(" ").append(decodeBoard(board(i)))
    }
    return builder.toString();
  }
}
object TUI {
  def main(args: Array[String]): Unit = {
    var TUI = new TUI();
    println(TUI.presentBoard())
  }
}
