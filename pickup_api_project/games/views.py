from django.shortcuts import render
from django.http import HttpResponse

def test(request):
    build_test_db()

    return HttpResponse("TEST RESPONSE")

def get_full_game_list(request):
    #fill in
    return HttpResponse("")

def build_test_db():
    #fill in