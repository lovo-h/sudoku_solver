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
      "000 000 000",
      "000 000 000",
      "000 000 000");
    
    var ss = new Sudoku_Solver();
    ss.print(rows);
    
    

  }



}