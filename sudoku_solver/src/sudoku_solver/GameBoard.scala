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
    								  List[Int](0, 0, 0, 0, 0, 0, 0, 0, 0)
    								  );
    
	def printRow(x: Int) {
	  val b = board(x);
	  for (x <- b) {
		println(x);
	  }
	}

  def makeMove(row: Int, col: Int, elem: Int) = {
    def makeRow(xs: List[Int], col: Int, elem: Int, acc: Int): List[Int] = xs match {
      case List() => Nil
      case x :: xs1 => if (col == acc) elem :: makeRow(xs1, col, elem, acc + 1)
                       else x :: makeRow(xs1, col, elem, acc + 1)
    }
    val r = board(col);
    board(row) = makeRow(r, col, elem, 0);
  }

	def evalBoard() {
	  
	  
	}

}


object Hello extends App {
 println("hello, world");
 val gb: GameBoard = new GameBoard();   
 gb.makeMove(1, 1, 1);
 gb.printRow(1);
}