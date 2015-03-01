# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Game',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('game_name', models.CharField(max_length=60)),
                ('game_time', models.DateTimeField(verbose_name=b'game time')),
                ('sport_type', models.IntegerField(default=0, choices=[(0, b'Other'), (1, b'Frisbee'), (2, b'Soccer'), (3, b'Football')])),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Location',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('location_name', models.CharField(max_length=60)),
                ('latitude', models.FloatField()),
                ('longitude', models.FloatField()),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.AddField(
            model_name='game',
            name='location',
            field=models.ForeignKey(to='games.Location'),
            preserve_default=True,
        ),
    ]
