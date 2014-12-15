package sudoku_solver_02

class Sudoku_Solver {
  var board = collection.mutable.Map[String, String]();
  var relations = collection.mutable.Map[String, collection.mutable.Map[String, collection.mutable.Map[String, String]]]();
  var dPlacesCount = 0;
  var dPlaces = "";

  def initializeBoard(): Unit = {
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        board += (row.toString + col.toString -> "123456789");
      }
    }
    // needed for solving
    setup_relations();
  }

  def processPuzzleInput(puzzle: String): Boolean = {
    var arrPuzzle = puzzle.toCharArray();
    var col = 0;
    var row = 0;

    var isValidSolution: Boolean = true;

    initializeBoard();

    for (i <- 0 until arrPuzzle.length) {
      if (arrPuzzle(i).toString.compareTo(".") == 0 || arrPuzzle(i).toString.compareTo("0") == 0) {
        //        System.out.println("Skip: " + i.toString);
      } else {
        if (!successfullySetElement(row.toString + col.toString, arrPuzzle(i).toString)) {
          isValidSolution = false;
        }
      }
      col += 1;
      if (col > 8) {
        col = 0;
        row += 1;
      }
    }

    if (isValidSolution) {
      return(solveCurrentBoard());
    } else {
      return false;
    }

    // return board when finished: return board
//    return true;
  }

  def solveCurrentBoard(): Boolean = {
    // if all elements have a single solution, it's already valid
    if (isCurrentBoardSolutionValid()) {
      return true;
    }

    var minElementVal = "123456789";
    var location = "00";

    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        var element = row.toString + col.toString;
        var elementVal = board(element);
        if (elementVal.length > 1) {
          if (elementVal.length < minElementVal.length) {
            minElementVal = elementVal;
            location = element;
          }
        }
      }
    }

    // just in case something goes wrong, we backup
    var backupCurrentBoard: collection.mutable.Map[String, String] = board.clone;
    var arrPossibleSolutions = board(location).toCharArray();
    var isPossibleNow: Boolean = false;

    for (sol <- 0 to arrPossibleSolutions.length - 1) {
      // TODO: WORKING HERE
      if (successfullySetElement(location, arrPossibleSolutions(sol).toString)) {
        if (!solveCurrentBoard) {
          board = backupCurrentBoard.clone;
          return false;
        } else {
          return true;
        }
      } else {
        board = backupCurrentBoard.clone;
        return false;
      }
    }
    return true;
  }

  def isCurrentBoardSolutionValid(): Boolean = {
    board.foreach { element =>
      if (board(element._1).length() != 1) {
        return false;
      }
    };
    return true;
  }

  def successfullySetElement(element: String, value: String): Boolean = {
    val remainingValuesofElement = board(element).replace(value, "");
    val arrRemainingValuesofElement = remainingValuesofElement.toCharArray();

    if (arrRemainingValuesofElement.length == 0)
      return true;

    for (ele <- 0 to arrRemainingValuesofElement.length - 1) {
      if (!deducePuzzle(element, arrRemainingValuesofElement(ele).toString)) {
        return false;
      }
    }
    return true;
  }

  // TODO: temporary, remove
  def print(msg: String) {
    System.out.println(msg);
  }

  def deducePuzzle(element: String, delVal: String): Boolean = {
    var currentEleVal = board(element);
    var bakCurrentEleVal = currentEleVal;

    if (!currentEleVal.contains(delVal)) { // if it's not in here, we cannot remove it
      return true;
    }

    currentEleVal = currentEleVal.replace(delVal, "");
    board(element) = currentEleVal;

    if (currentEleVal.length == 0) {
      board(element) = bakCurrentEleVal; // abort, something went wrong
      return false;
    } else if (currentEleVal.length == 1) {
      var d2 = currentEleVal; // TODO: check if replaceable by cEV
      var all_relations = relations(element)("all"); // key-value map
      all_relations.foreach { key =>
        if (!deducePuzzle(key._1, d2)) {
          return false;
        }
      };
    }
    var row_relations = relations(element)("rows"); // key-value map
    var col_relations = relations(element)("cols"); // key-value map
    var sect_relations = relations(element)("sects"); // key-value map

    if (!isValidInGroupRelation(row_relations, delVal, element, "ROW")) {
      return false;
    }
    if (!isValidInGroupRelation(col_relations, delVal, element, "COL")) {
      return false;
    }
    if (!isValidInGroupRelation(sect_relations, delVal, element, "SECT")) {
      return false;
    }

    return true;
  }

  def isValidInGroupRelation(type_relations: collection.mutable.Map[String, String], delVal: String, eleTest: String, relationType: String): Boolean = {
    dPlaces = "";
    dPlacesCount = 0;

    type_relations.foreach { key =>
      if (board(key._1).contains(delVal)) {
        dPlaces = key._1;
        dPlacesCount += 1;
      }
    };

    if (dPlacesCount == 0) {
      return false;
    } else if (dPlacesCount == 1) {
      if (!successfullySetElement(dPlaces, delVal)) {
        return false;
      }
    }

    return true;
  }

  def setup_relations() {
    for (row <- 0 until 9) {
      for (col <- 0 until 9) {
        val sectInt = convertRowColToSect(row, col);
        val rows = getRow(row, col);
        val cols = getCol(col, row);
        val sects = getSect(sectInt, row, col);
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

  def getRow(row: Int, colInt: Int): collection.mutable.Map[String, String] = {
    val rows = collection.mutable.Map[String, String]();
    for (col <- 0 until 9) {
      if (col != colInt)
        rows += (row.toString + col.toString -> board(row.toString + col.toString));
    }
    rows;
  }

  def getCol(col: Int, rowInt: Int): collection.mutable.Map[String, String] = {
    val cols = collection.mutable.Map[String, String]();
    for (row <- 0 until 9) {
      if (row != rowInt)
        cols += (row.toString + col.toString -> board(row.toString + col.toString));
    }
    cols;
  }

  def getSect(sectInt: Int, rowInt: Int, colInt: Int): collection.mutable.Map[String, String] = {
    val sect = collection.mutable.Map[String, String]();

    val rs = if (sectInt > 5) 6 else if (sectInt < 3) 0 else 3;
    val re = rs + 3;
    val cs = (sectInt % 3) * 3;
    val ce = cs + 3;

    for (row <- rs until re) {
      for (col <- cs until ce) {
        if (row != rowInt || col != colInt)
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