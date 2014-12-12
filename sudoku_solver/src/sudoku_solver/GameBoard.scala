package sudoku_solver

class GameBoard {
  val board: Array[List[Int]] = Array(
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0),
    List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0));

  def printRow(x: Int) {
    val b = board(x);
    for (x <- b) {
      println(x);
    }
  }


  
  def printBoard() {
    val b = board;
    for (row <- b) {
      for (elem <- row) {
        print(elem + " ");
      }
      println();
    }
  }
  


  /*
   * Inputs: int row (0-8) corresponding to each row on the game board, 
   *         int element corresponding to the number to be placed (1-9)
   *         
   * makeMove will update the game board and so doesn't return anything
   */
  def makeMove(row: Int, col: Int, elem: Int) = {
    def makeRow(xs: List[Int], col: Int, elem: Int, acc: Int): List[Int] = xs match {
      case List() => Nil
      case x :: xs1 => if (col == acc) elem :: makeRow(xs1, col, elem, acc + 1)
      else x :: makeRow(xs1, col, elem, acc + 1)
    }
    val r = board(row);
    board(row) = makeRow(r, col, elem, 0);
  }

  /*
   * Evaluates the game board by calling evalRows(), evalColumns(), and evalBoxes()
   * 
   * Returns false if any of the three methods calls return false
   */
  def evalBoard(): Boolean = {
    if(evalRows() && evalColumns()) true
    else false
  }

  /*
	 * Loops through each row in the game board and checks that each element in each row is unique
	 */
  def evalRows(): Boolean = {

    val b = board;
    val returnVal = false
    for (row <- b) {
      if (evalRow(row, 9) == false) return false
    }
    true
  }

  /*
   * helper function to evalRows() - evaluates one row
   */
  def evalRow(xs: List[Int], acc: Int): Boolean = {
    if (acc == 0) true
    else if (xs.count(_ == acc) == 1) evalRow(xs, acc - 1)
    else false
  }

  /*
   * loops through each column on the game board to check that each
   * element in each column is unique
   */
  def evalColumns(): Boolean = {
    val b = board;
    def evalLoop(acc: Int): Boolean = {
      if(acc == 9) return true
      val emptyList: List[Int] = Nil;
      val list: List[Int] = getColumn(emptyList, acc, 0);
     // println(list);
      if (evalRow(list, 9)) evalLoop(acc + 1)
      else return false
    }
    evalLoop(0)

  }

  /* helper function to getColumns()
   * returns the element at the position given by col
   */
  def getColumnElement(xs: List[Int], col: Int, acc: Int): Int = {
      //print(col);
      xs(col)
 //   if (acc == col) xs.head
 //   else getColumnElement(xs.tail, col, acc + 1)
  }

  /*
   * helper function to evalColumns()
   * returns the column (as a list) indicated by col
   */
  def getColumn(column: List[Int], col: Int, acc: Int): List[Int] = {
    if (acc == 8) getColumnElement(board(acc), col, 0) :: Nil
    else getColumnElement(board(acc), col, 0) :: getColumn(column, col, acc + 1)
  }
}

object Hello extends App {
  
	/*
	 * Takes in an array of numbers to fill board from left to right starting with the first row
	 */
    def fillBoard(list: Array[Int], gb: GameBoard) = {
    val row = 0;
    val col = 0;
    def loop(row: Int, col: Int, acc: Int): Boolean = {
      if (acc == 80) {
        gb.makeMove(row, col, list(acc));
        return true
      }
      else if (col == 8) {
        gb.makeMove(row, col, list(acc));
        loop(row+1, 0, acc+1);
      }
      else {
        gb.makeMove(row, col, list(acc));
        loop(row, col+1, acc+1);
      }
      true
    } 
    loop(0,0,0)
  }
  
  println("hello, world");
  val gb1: GameBoard = new GameBoard();
  val list = Array(3,7,9,8,2,1,4,5,6,5,4,8,9,3,6,7,2,1,2,6,1,4,5,7,3,9,8,
                                 1,8,5,3,6,9,2,4,7,9,2,4,1,7,5,8,6,3,6,3,7,2,8,4,9,1,5,  
                                 4,9,3,5,1,8,6,7,2,8,5,6,7,9,2,1,3,4,7,1,2,6,4,3,5,8,9
                                 );
  fillBoard(list, gb1);
  
  gb1.printBoard();
  print(gb1.evalBoard());
}

