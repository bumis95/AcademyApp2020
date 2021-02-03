package com.androidacademy.academyapp2020.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androidacademy.academyapp2020.repositories.local.MoviesDbContract

@Entity(tableName = MoviesDbContract.Movies.TABLE_NAME)
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_MOVIE_ID)
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float, // vote_average
    val numberOfRatings: Int, // vote_count
    val minimumAge: Int, // adult
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)
