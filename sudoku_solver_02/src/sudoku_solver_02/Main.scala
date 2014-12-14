package sudoku_solver_02

import java.io.File

object Main {
  // main
  def main(args: Array[String]): Unit = {
    var puzzle = "...........5....9...4....1.2....3.5....7.....438...2......9.....1.4...6..........";
    var ss = new Sudoku_Solver();

    ss.initializeBoard(puzzle);

    //    System.out.println("BOARD:");
    //    printGameBoard(ss.getBoard());
    //    System.out.println("SECT: 3");
    //    printSect(ss.getSect(2), 2);
    //    System.out.println("COL:1");
    //    printCol(ss.getCol(0), 0);
    //    printCol(ss.getBoard, 0);
    //    System.out.println("ROW: 1");
    //    printRow(ss.getBoard, 0);

  }

  def printGameBoard(board: collection.mutable.Map[String, String]): Unit = {
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        System.out.print(board(row.toString + col.toString));
        if ((col + 1) % 3 == 0) System.out.print(" ");
      }
      if (row == 2 || row == 5) System.out.println();
      System.out.println();
    }
  }

  def printCol(board: collection.mutable.Map[String, String], col: Int): Unit = {
    for (row <- 0 until 9) {
      System.out.print(board(row.toString + col.toString) + " ");
    }
    System.out.println();
  }

  def printRow(board: collection.mutable.Map[String, String], row: Int): Unit = {
    for (col <- 0 until 9) {
      System.out.print(board(row.toString + col.toString) + " ");
    }
    System.out.println();
  }

  def printSect(board: collection.mutable.Map[String, String], sectInt: Int): Unit = {
    val rs = if (sectInt > 5) 6 else if (sectInt < 3) 0 else 3;
    val re = rs + 3;
    val cs = (sectInt % 3) * 3;
    val ce = cs + 3;

    for (row <- rs until re) {
      for (col <- cs until ce) {
        System.out.print(board(row.toString + col.toString));
      }
      System.out.println();
    }
  }

}