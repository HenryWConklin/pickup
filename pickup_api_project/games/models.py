from django.db import models

class Location(models.Model):
    location_name = models.CharField(max_length=60)
    latitude = models.FloatField()
    longitude = models.FloatField()

class Game(models.Model):
    game_name = models.CharField(max_length=60) #max_length can be whatever
    game_time = models.DateTimeField('game time')
    location = models.ForeignKey(Location)
    SPORTS = (
        (0, 'Other'),
        (1, 'Frisbee'),
        (2, 'Soccer'),
        (3, 'Football'),
    )
    sport_type = models.IntegerField(choices=SPORTS, default = 0)


