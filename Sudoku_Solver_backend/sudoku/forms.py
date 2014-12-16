from django import forms
from django.forms import CharField, TextInput, IntegerField


class SudokuPuzzleForm(forms.Form):
    r0c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r0c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r1c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r1c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r2c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r2c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r3c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r3c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r4c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r4c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r5c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r5c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r6c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r6c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r7c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r7c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))

    r8c0 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c1 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c2 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c3 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c4 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c5 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c6 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c7 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))
    r8c8 = CharField(max_length=1, label="", widget=TextInput(
        attrs={'size': '2', 'class': 'form-control'}))