package sudoku_solver_02

class Sudoku_Solver {
  // print array of strings
  def print(sb: Array[String]): Unit = {
    for (i <- 0 until sb.length) {

      System.out.println(sb(i));

      if ((i + 1) % 3 == 0) {
        System.out.println();
      }
    }
  }
}