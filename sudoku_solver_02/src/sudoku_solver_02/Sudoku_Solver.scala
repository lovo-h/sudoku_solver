package sudoku_solver_02

class Sudoku_Solver {
  val board = collection.mutable.Map[String, String]();
  val relations = collection.mutable.Map[String, collection.mutable.Map[String, collection.mutable.Map[String, String]]]();

  def initializeBoard(puzzle: String): Unit = {
    var chars = puzzle.toCharArray();
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        board += (row.toString + col.toString -> chars(9 * row + col).toString);
      }
    }
  }

  def start(): Unit = {
    setup_relations();

  }

  def setup_relations() {
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        val sectInt = convertRowColToSect(row, col);
        val rows = getRow(row);
        val cols = getCol(col);
        val sects = getSect(sectInt);
        val all_relations = rows ++ cols ++ sects;

        val mapElementAttrs = collection.mutable.Map[String, collection.mutable.Map[String, String]]();
        mapElementAttrs += ("all" -> all_relations);
        mapElementAttrs += ("rows" -> rows);
        mapElementAttrs += ("cols" -> cols);
        mapElementAttrs += ("sects" -> sects);

        relations += (row.toString + col.toString -> mapElementAttrs);
        
        System.out.println(relations);

      }
    }
  }

  def convertRowColToSect(row: Int, col: Int): Int = {
    if (row < 3) {
      if (col < 3) {
        0;
      } else if (col > 5) {
        2;
      } else {
        1;
      }
    } else if (row > 5) {
      if (col < 3) {
        6;
      } else if (col > 5) {
        8;
      } else {
        7;
      }
    } else {
      if (col < 3) {
        3;
      } else if (col > 5) {
        5;
      } else {
        4;
      }
    }
  }

  // getters
  def getBoard(): collection.mutable.Map[String, String] = {
    board;
  }

  def getRow(row: Int): collection.mutable.Map[String, String] = {
    val rows = collection.mutable.Map[String, String]();
    for (col <- 0 until 9) {
      rows += (row.toString + col.toString -> board(row.toString + col.toString));
    }
    rows;
  }

  def getCol(col: Int): collection.mutable.Map[String, String] = {
    val cols = collection.mutable.Map[String, String]();
    for (row <- 0 until 9) {
      cols += (row.toString + col.toString -> board(row.toString + col.toString));
    }
    cols;
  }

  def getSect(sectInt: Int): collection.mutable.Map[String, String] = {
    val sect = collection.mutable.Map[String, String]();

    val rs = if (sectInt > 5) 6 else if (sectInt < 3) 0 else 3;
    val re = rs + 3;
    val cs = (sectInt % 3) * 3;
    val ce = cs + 3;

    for (row <- rs until re) {
      for (col <- cs until ce) {
        sect += (row.toString + col.toString -> board(row.toString + col.toString));
      }
    }

    sect;
  }

}