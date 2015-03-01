from django.conf.urls import patterns, url
from games import views

urlpatterns = patterns('',
        url(r'^$', views.test, name='index'),
        url(r'^test', views.test, name='test'),
        url(r'^game_list/$', views.get_full_game_list, name='get all games'),
        )