package com.androidacademy.academyapp2020.repositories.local

import androidx.room.TypeConverter
import com.androidacademy.academyapp2020.models.Actor
import com.androidacademy.academyapp2020.models.Genre
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MovieConverter {

    private val json = Json {
        coerceInputValues = true
    }

    @TypeConverter
    fun fromActor(actors: List<Actor>): String = json.encodeToString(actors)

    @TypeConverter
    fun toActors(actorsJson: String): List<Actor> = json.decodeFromString(actorsJson)

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String = json.encodeToString(genres)

    @TypeConverter
    fun toGenre(genresJson: String): List<Genre> = json.decodeFromString(genresJson)
}