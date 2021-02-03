package com.androidacademy.academyapp2020.repositories.local

import androidx.room.TypeConverter
import com.androidacademy.academyapp2020.models.Genre

class GenreConverter {

    @TypeConverter
    fun fromGenre(genres: List<Genre>): String = genres.joinToString { it.name }

    @TypeConverter
    fun toGenre(data: String): List<Genre> = listOf()
}