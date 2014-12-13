package sudoku_solver_02

import java.io.File

object Main {
  // main
  def main(args: Array[String]): Unit = {
    val rows = Array(
      "000 000 000",
      "000 000 000",
      "000 000 000",

      "000 000 000",
      "000 000 000",
      "000 000 000",

      "000 123 456",
      "000 000 000",
      "123 000 000");

    System.out.println("INITIALIZED BOARD");
    var ss = new Sudoku_Solver();
    ss.print_board(rows);
    System.out.println("ROW: 9");
    ss.print_row(rows, 8);
    System.out.println("COL: 2");
    ss.print_col(rows, 1);
    System.out.println("SECT: 8");
    ss.print_sect(rows, 7)

  }

}