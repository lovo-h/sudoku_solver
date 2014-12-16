from django.shortcuts import render, HttpResponseRedirect
from forms import SudokuPuzzleForm
import time
import os.path


# Create your views here.

def parse_solution(solution):
    ret_sol = {}
    elem = ""
    count = 0
    for row in range(0, 9):
        for col in range(0, 9):
            elem = "r" + str(row) + "c" + str(col)
            ret_sol[elem] = solution[count:count + 1]
            count += 1

    return ret_sol


def solvePuzzle(request):
    if request.method == 'POST':
        err = ""
        if 'clear' in request.POST:
            form = SudokuPuzzleForm()
        elif 'submit' in request.POST:
            sol = process(request.POST)
            if '0' in sol[0:1]:
                form = SudokuPuzzleForm(request.POST)
                err = "No Solution Found"
            else:
                init = parse_solution(sol)
                form = SudokuPuzzleForm(init)


        context = {'sudoku_form': form, 'err': err}

        return render(request, 'sudoku/index.html', context)
    else:
        form = SudokuPuzzleForm()
        context = {'sudoku_form': form}
        return render(request, 'sudoku/index.html', context)


def process(elements):
    puzzle = ""
    for row in range(0, 9):
        for col in range(0, 9):
            ele = "r" + str(row) + "c" + str(col)
            if elements[ele] == "":
                puzzle += "."
            else:
                puzzle += elements[ele]

    file_problem = "./sudoku_solver_02/problem.txt"
    file_solution = "./sudoku_solver_02/solution.txt"
    my_returned_solution = ""

    with open(file_problem, "w") as txtProblem:
        txtProblem.write(puzzle)

    # Allow Scala some time to solve this

    while not os.path.isfile(file_solution):
        time.sleep(1)

    with open(file_solution, "r") as txtSolution:
        my_returned_solution = txtSolution.read()

    time.sleep(0.5)

    try:
        os.remove(file_solution)
    except OSError:
        print "Failed to delete"

    return my_returned_solution



