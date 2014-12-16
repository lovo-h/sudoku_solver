from django.shortcuts import render, HttpResponseRedirect
from forms import SudokuPuzzleForm
import time, random
import os.path

# Create your views here.

def parse_solution(solution):
    ret_sol = {}
    elem = ""
    count = 0
    for row in range(0, 9):
        for col in range(0, 9):
            elem = "r" + str(row) + "c" + str(col)
            if solution[count:count + 1] == '.':
                ret_sol[elem] = ""
            else:
                ret_sol[elem] = solution[count:count + 1]
            count += 1

    return ret_sol

def parse_solution2(solution, puzzle):
    count = 0
    arrPuzzle = []

    for c in puzzle:
        if c == '.':
            arrPuzzle.append(count)
        count += 1

    num = random.choice(arrPuzzle)
    ret_puzzle = puzzle[0:num] + solution[num:num+1] + puzzle[num+1:]

    return parse_solution(ret_puzzle), ret_puzzle

def solvePuzzle(request):
    if request.method == 'POST':
        err = ""
        if 'clear' in request.POST:
            form = SudokuPuzzleForm()
        elif 'hint' in request.POST:
            solNotIn = 'sol' not in request.session
            puzNotIn = 'puz' not in request.session
            oldPuzNotEq =  getPuzzle(request.POST) != request.session['puz']
            if solNotIn or puzNotIn or oldPuzNotEq:
                sol = process(request.POST)
                puzzle = getPuzzle(request.POST)
                request.session['sol'] = sol
                request.session['puz'] = puzzle
            else:
                puzzle = request.session['puz']
                sol = request.session['sol']

            if '0' in sol[0:1]:
                form = SudokuPuzzleForm(request.POST)
                err = "No Solution Found"
            else:
                init, ret_puz = parse_solution2(sol, puzzle)
                request.session['puz'] = ret_puz
                form = SudokuPuzzleForm(init)


        elif 'submit' in request.POST:
            sol = process(request.POST, 'submit')
            if '0' in sol[0:1]:
                form = SudokuPuzzleForm(request.POST)
                err = "No Solution Found"
            elif '.' not in sol:
                init = parse_solution(sol)
                form = SudokuPuzzleForm(init)
            else:
                init = parse_solution(sol)
                form = SudokuPuzzleForm(init)

        context = {'sudoku_form': form, 'err': err}

        return render(request, 'sudoku/index.html', context)
    else:
        form = SudokuPuzzleForm()
        context = {'sudoku_form': form}
        return render(request, 'sudoku/index.html', context)


def getPuzzle(elements):
    puzzle = ""
    for row in range(0, 9):
        for col in range(0, 9):
            ele = "r" + str(row) + "c" + str(col)
            if elements[ele] == "":
                puzzle += "."
            else:
                puzzle += elements[ele]
    return puzzle

def process(elements):
    puzzle = getPuzzle(elements)

    # if type == 'hint':
    #     puzzle += "h"
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
        while os.path.isfile(file_solution):
            os.remove(file_solution)
    except OSError:
        print "Failed to delete"

    return my_returned_solution



