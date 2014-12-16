package sudoku_solver_02

import java.io.FileOutputStream
import java.io.File
import scala.actors._
import sudoku_solver_02.Sudoku_Solver
import scala.util.control._

object Main {
  // main
  def main(args: Array[String]): Unit = {
    var puzzle = "1..97.5422....571.657.2..3.3..19465..6.832.7..18567..3.2..4.397.362....1894.13..5";
    var fileProblem = new File("C:\\Users\\lovo-h\\Documents\\GitHub\\sudoku_solver\\Sudoku_Solver_backend\\sudoku_solver_02\\problem.txt");
    var fileSolution = new File("C:\\Users\\lovo-h\\Documents\\GitHub\\sudoku_solver\\Sudoku_Solver_backend\\sudoku_solver_02\\solution.txt");
    var txtToWriteToFile = "0";
    var ss = new Sudoku_Solver();
    var arrP: Array[Integer] = Array.fill(81)(0);
    var r = new scala.util.Random;
    var contentInBytes: Array[Byte] = null;
    var count = 0;
    var num = 0;

    while (true) {
      var hint = false; // TODO: Remove
      txtToWriteToFile = "0";

      while (!fileProblem.exists()) {
        Thread.sleep(1500);
      }

      val source = scala.io.Source.fromFile(fileProblem);
      var puzzleToSolve: String = source.mkString
      source.close();
      
      System.out.println(puzzleToSolve + "  PuzzleToSolve");

      var isHint = puzzleToSolve.charAt(puzzleToSolve.length - 1) == 'h';
      if (isHint) puzzleToSolve = puzzleToSolve.substring(0, puzzleToSolve.length - 1); // remove 'h'
      var isSolvable: Boolean = ss.processPuzzleInput(puzzleToSolve);

      if (isSolvable) {
        txtToWriteToFile = printGameBoard(ss.getBoard);
        System.out.println(txtToWriteToFile + "  Solution" );
      }

      if (isSolvable && isHint) {
        count = 0;
//        println("hint");
        for (el <- 0 until 81) {
          if (puzzleToSolve.charAt(el) == '.') {
            arrP(count) = el;
            count += 1;
          }
        }
        if (count > 0) {
          num = r.nextInt(count);
          txtToWriteToFile = puzzleToSolve.substring(0, arrP(num)) + txtToWriteToFile.charAt(arrP(num)) + puzzleToSolve.substring(arrP(num) + 1, 81);
        }
      }

      println(txtToWriteToFile + "  txtToWriteToFile" );

      if (!fileSolution.exists()) {
        fileSolution.createNewFile();
      }

      //      if (hint) {
      //        val puzzle = puzzleToSolve.substring(0, puzzleToSolve.length - 1);
      //        val hintToWriteToFile = getHintText(txtToWriteToFile, puzzle);
      //        println(hintToWriteToFile.toString());
      //        var hintInBytes = getHintText(txtToWriteToFile, puzzleToSolve);
      //        fop.write(hintInBytes.getBytes());
      //      } else {
      contentInBytes = txtToWriteToFile.getBytes();
      var fop = new FileOutputStream(fileSolution);
      fop.write(contentInBytes);
      //      }
      fop.flush();
      fop.close();

      if (fileProblem.exists()) {
        fileProblem.delete();
      }
    }
  }

  def getHintText(hint: String, puzzle: String): String = {
    //  println("getHintText");
    var returnText = "";
    var puzzleArray = puzzle.toCharArray();
    var hintArray = hint.toCharArray();

    var r = new scala.util.Random;
    var num = r.nextInt(81);
    do {
      num = r.nextInt(81);
    } while (puzzleArray(r.nextInt(81)) != '.')

    for (i <- 0 until puzzleArray.length) {
      if (i == num) {
        returnText += hintArray(i);
        //    println(hintArray(i));
      } else {
        //   if (puzzleArray(i) == '.') {
        //     println("found . ");
        //     returnText += " ";
        //   } else {
        returnText += puzzleArray(i);
        //   }
      }
    }
    return returnText;
  }

  def getHintIndex(input: String): Int = {
    if (input.length < 2) return 0
    var arrPuzzle = input.toCharArray()
    var count = 0;
    val loop = new Breaks;
    loop.breakable {
      for (i <- 0 until arrPuzzle.length) {
        if (arrPuzzle(i).toString.compareTo(".") == 0 || arrPuzzle(i).toString.compareTo("0") == 0) {
          loop.break;
        } else {
          count = count + 1;
        }
      }
    }
    return count;
  }

  def printGameBoard(board: collection.mutable.Map[String, String]): String = {
    var strBoard = "";
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        strBoard += board(row.toString + col.toString);
//        System.out.print(board(row.toString + col.toString) + " ");
//        if ((col + 1) % 3 == 0) System.out.print("  ");
      }
//      if (row == 2 || row == 5) System.out.println();
//      System.out.println();
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