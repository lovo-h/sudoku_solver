package sudoku_solver_02

class Sudoku_Solver {
  // print array of strings
  def print_board(sb: Array[String]): Unit = {
    for (i <- 0 until 9) {
      System.out.println(sb(i));
      // add filler to delineate sections  
      if ((i + 1) % 3 == 0) {
        System.out.println();
      }
    }
  }

  def print_col(sb: Array[String], col: Int): Unit = {
    for (i <- 0 until 9) {
      System.out.println(sb(i).substring(col, col + 1));
    }
  }

  def print_row(sb: Array[String], row: Int): Unit = {
    System.out.println(sb(row));
  }

  def print_sect(sb: Array[String], sect: Int): Unit = {
    val rs = if (sect > 5) 6 else (if (sect < 3) 0 else 3); // row start
    val re = rs + 3; // row end
    val col = (sect % 3) * 3; // determine column adjustment - due to spaces
    val cs = if (col > 5) 8 else (if (col > 2) 4 else 0); // col start
    val ce = cs + 3; // col end

    for (row <- rs until re) {
      System.out.println(sb(row).substring(cs, ce));
    }

  }

}