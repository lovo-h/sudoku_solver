from django.conf.urls import patterns, url
from sudoku.views import solvePuzzle

urlpatterns = patterns('',
                       url(r'^', solvePuzzle),
)
