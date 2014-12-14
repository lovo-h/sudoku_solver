package sudoku_solver_02

class Sudoku_Solver {
  val board = collection.mutable.Map[String, String]();

  val sections = Map(
    0 -> "Hello",
    1 -> "World",
    2 -> "World",
    3 -> "World",
    4 -> "World",
    5 -> "World",
    6 -> "World",
    7 -> "World",
    8 -> "World");

  def initializeBoard(puzzle: String): Unit = {
    var chars = puzzle.toCharArray();
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        board += (row.toString + col.toString -> chars(9*row+col).toString);
      }
    }
  }
  
  def getBoard(): collection.mutable.Map[String, String] = {
    board;
  }

  def defineRelations(): Unit = {

  }

  // print array of strings
  //  def get_board(): Array[String] = {
  //    board;
  //  }
  //
  //  def get_col(col: Int): Array[String] = {
  //    var ret: Array[String] = new Array[String](9);
  //    for (i <- 0 until 9) {
  //      ret(i) = board(i).substring(col, col + 1);
  //    }
  //    ret;
  //  }
  //
  //  def get_row(row: Int): Array[String] = {
  //    var ret: Array[String] = new Array[String](9);
  //    board(row);
  //    ret;
  //  }
  //
  //  def print_sect(sb: Array[String], sect: Int): Unit = {
  //    val rs = if (sect > 5) 6 else (if (sect < 3) 0 else 3); // row start
  //    val re = rs + 3; // row end
  //    val col = (sect % 3) * 3; // determine column adjustment - due to spaces
  //    val cs = if (col > 5) 8 else (if (col > 2) 4 else 0); // col start
  //    val ce = cs + 3; // col end
  //
  //    for (row <- rs until re) {
  //      System.out.println(sb(row).substring(cs, ce));
  //    }
  //
  //  }

}