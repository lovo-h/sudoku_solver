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
    var fileProblem = new File("C:\\Users\\Dylan Doggett\\Documents\\GitHub\\sudoku_solver\\Sudoku_Solver_backend\\sudoku_solver_02\\problem.txt");
    var fileSolution = new File("C:\\Users\\Dylan Doggett\\Documents\\GitHub\\sudoku_solver\\Sudoku_Solver_backend\\sudoku_solver_02\\solution.txt");
    var txtToWriteToFile = "0";
    var ss = new Sudoku_Solver();
    
    while (true) {
      var hint = false;    
      var playBoolean = false;
      txtToWriteToFile = "0";
      
      while (!fileProblem.exists()) {
        Thread.sleep(1500);
      }

      val source = scala.io.Source.fromFile(fileProblem);
      val puzzleToSolve = source.mkString
      source.close();
      
      var playTextToWriteToFile = "";
      if(puzzleToSolve == "play") {
        playBoolean = true;
        var r = new scala.util.Random;
        var num1 = r.nextInt(81);    
        var num2 = r.nextInt(81); 
        
        for(i <- 0 until 81) {
          if(i == num1) {
            playTextToWriteToFile += (r.nextInt(8) + 1);
          }
          else if (i == num2) {
            playTextToWriteToFile += (r.nextInt(8) + 1);
          }
          else {
            playTextToWriteToFile += "."
          }
        }
        println(playTextToWriteToFile);
      }
      
      if (puzzleToSolve.charAt(puzzleToSolve.length - 1) == 'h') {
        println("hint");
        hint = true;
      }
   
      if (hint) {
        if (ss.processPuzzleInput(puzzleToSolve.substring(0, puzzleToSolve.length - 1))) {
          txtToWriteToFile = printGameBoard(ss.getBoard);
        }
      } else if (playBoolean) {
        if (ss.processPuzzleInput(playTextToWriteToFile)) {
          txtToWriteToFile = printGameBoard(ss.getBoard);
        }        
      }      
      else {
        if (ss.processPuzzleInput(puzzleToSolve)) {
          txtToWriteToFile = printGameBoard(ss.getBoard);
        }
      }
      
      
      var fop = new FileOutputStream(fileSolution);
      println("txtToWriteToFile: " + txtToWriteToFile);

      if (!fileSolution.exists()) {
        fileSolution.createNewFile();
      }

      if (hint) {
        val puzzle = puzzleToSolve.substring(0, puzzleToSolve.length - 1);
        val hintToWriteToFile = getHintText(txtToWriteToFile, puzzle);
        println(hintToWriteToFile.toString());
        var hintInBytes = getHintText(txtToWriteToFile, puzzleToSolve);
        fop.write(hintInBytes.getBytes());
      } 
      else if (playBoolean) {
        println("playBoolean");
        playTextToWriteToFile = "";
        var r = new scala.util.Random;
        
        var list: List[Int] = Nil
        for(i <- 0 until 18) {
          var num = r.nextInt(81); 
        //  print(num)
          do {
          num = r.nextInt(81); 
          } while (list.count(_ == num) != 0)
          list = num :: list
        }
        
      // println(list);
       list = list.sortWith(_ < _);
       println(list);

        //var playArray: Array[Int] = list.toArray

        var count = 0;
        for (i <- 0 until txtToWriteToFile.length) {
          if (count < 18) {
            if (i == list(count)) {
              //println("i == list(count): " + i + ", " + count );
              playTextToWriteToFile += txtToWriteToFile.charAt(i);
              count += 1
            }
            else {
              playTextToWriteToFile += '.';
            }
          } else {
            playTextToWriteToFile += '.';
          }
        }
       
       println("playTXTtofile: " + playTextToWriteToFile);
       
       var playInBytes = playTextToWriteToFile.getBytes();
       fop.write(playInBytes);  
      }
      else {
        var contentInBytes = txtToWriteToFile.getBytes();
        fop.write(contentInBytes);
      }
      fop.flush();
      fop.close();

      if (fileProblem.exists()) {
        fileProblem.delete();
      }
    }
  }

  def getHintText(hint: String, puzzle: String): String = {
    println("getHintText");
    var returnText = "";
    var puzzleArray = puzzle.toCharArray();
    var hintArray = hint.toCharArray();
    
    var r = new scala.util.Random;
    var num = r.nextInt(81);
    do {
      num = r.nextInt(81);
    } while (puzzleArray(r.nextInt(81)) != '.')     
    
      
    for (i <- 0 until puzzleArray.length) {
      println(hintArray);
      if (i == num) {
        returnText += hintArray(i);
     //   println(hintArray(i));
      }
      else {
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
  
  // Not used anymore
  def getHintIndex(input: String): Int = {
    var arrPuzzle = input.toCharArray()
    var count = 0;
    val loop = new Breaks;
    loop.breakable {
    for (i <- 0 until arrPuzzle.length) {
      if (arrPuzzle(i).toString.compareTo(".") == 0 || arrPuzzle(i).toString.compareTo("0") == 0) {
    	  loop.break;
      } 
      else{
        count = count + 1 ;
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