package com.androidacademy.academyapp2020.repositories.local

import androidx.room.TypeConverter
import com.androidacademy.academyapp2020.models.Actor

class ActorConverter {

    @TypeConverter
    fun fromActor(actors: List<Actor>): String = actors.joinToString { it.name }

    @TypeConverter
    fun toActors(json: String): List<Actor> = listOf()
}