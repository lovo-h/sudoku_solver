package sudoku_solver_02

class Sudoku_Solver {
  val board = collection.mutable.Map[String, String]();
  val relations = collection.mutable.Map[String, collection.mutable.Map[String, collection.mutable.Map[String, String]]]();

  def initializeBoard(puzzle: String): Unit = {
    var chars = puzzle.toCharArray();
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        board += (row.toString + col.toString -> "123456789");
      }
    }
    // needed for solving
    setup_relations();
  }

  def start(): Unit = {

  }

  def processPuzzleInput(puzzle: String): Boolean = {
    var arrPuzzle = puzzle.toCharArray();
    var col = 0;
    var row = 0;

    for (i <- 0 until arrPuzzle.length) {
      if (arrPuzzle(i).toString.compareTo(".") == 0 || arrPuzzle(i).toString.compareTo("0") == 0) {
        System.out.println("Skip");
      } else {
        if (!successfullySetElement(row.toString + col.toString, arrPuzzle(i).toString)) {
          false;
        }
      }
      col += 1;
      if (col > 8) {
        col = 0;
        row += 1;
      }
    }
    true;
  }

  def successfullySetElement(element: String, value: String): Boolean = {
    val arrRemainingValuesofElement = board(element).toCharArray();

    for (ele <- 0 to arrRemainingValuesofElement.length) {
      if (!deducePuzzle(element, arrRemainingValuesofElement(ele).toString)) {
        false;
      }
    }
    true;
  }

  def deducePuzzle(element: String, delVal: String): Boolean = {
    var currentEleVal = board(element);
    var bakCurrentEleVal = currentEleVal;

    if (!currentEleVal.contains(delVal)) { // if it's not in here, we cannot remove it
      true;
    }

    currentEleVal = currentEleVal.replace(delVal, "");
    board(element) = currentEleVal;

    if (currentEleVal.length == 0) {
      board(element) = bakCurrentEleVal; // abort, something went wrong
      false;
    } else if (currentEleVal.length == 1) {
      var d2 = currentEleVal; // TODO: check if replaceable by cEV
      var all_relations = relations(element)("all"); // key-value map
      all_relations.foreach { key =>
        if (!deducePuzzle(key._1, d2)) {
          false;
        }
      };
    }
    // Working Here: need to add one final piece of logic
    true;
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

  // ================================================ GETTERS
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

//  def initializeBoard(puzzle: String): Unit = {
//    var chars = puzzle.toCharArray();
//    for (row <- 0 until 9) {
//      for (col <- 0 until 9) {
//        board += (row.toString + col.toString -> chars(9 * row + col).toString);
//      }
//    }
//    // needed for solving
//    setup_relations();
//  }