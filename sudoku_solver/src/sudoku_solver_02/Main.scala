package sudoku_solver_02

import java.io.FileOutputStream
import java.io.File
import scala.actors._

object Main {
  // main
  def main(args: Array[String]): Unit = {
    var puzzle = "1..97.5422....571.657.2..3.3..19465..6.832.7..18567..3.2..4.397.362....1894.13..5";
    var fileProblem = new File("C:\\Users\\lovo-h\\PycharmProjects\\Sudoku_Solver\\sudoku_solver_02\\problem.txt");
    var fileSolution = new File("C:\\Users\\lovo-h\\PycharmProjects\\Sudoku_Solver\\sudoku_solver_02\\solution.txt");
    var txtToWriteToFile = "0";
    var ss = new Sudoku_Solver();

    while (true) {
      txtToWriteToFile = "0";
      while (!fileProblem.exists()) {
        Thread.sleep(1500);
      }

      val source = scala.io.Source.fromFile(fileProblem);
      val puzzleToSolve = source.mkString
      source.close();

      if (ss.processPuzzleInput(puzzleToSolve)) {
        txtToWriteToFile = printGameBoard(ss.getBoard);
      }

      var fop = new FileOutputStream(fileSolution);

      if (!fileSolution.exists()) {
        fileSolution.createNewFile();
      }

      var contentInBytes = txtToWriteToFile.getBytes();

      fop.write(contentInBytes);
      fop.flush();
      fop.close();

      if (fileProblem.exists()) {
        fileProblem.delete();
      }
    }
  }

  def printGameBoard(board: collection.mutable.Map[String, String]): String = {
    var strBoard = "";
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        strBoard += board(row.toString + col.toString);
        System.out.print(board(row.toString + col.toString) + " ");
        if ((col + 1) % 3 == 0) System.out.print("  ");
      }
      if (row == 2 || row == 5) System.out.println();
      System.out.println();
    }

    return strBoard;
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