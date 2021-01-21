//package com.androidacademy.academyapp2020.models.entities
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.androidacademy.academyapp2020.models.Actor
//import com.androidacademy.academyapp2020.models.Genre
//import com.androidacademy.academyapp2020.repositories.local.MoviesDbContract
//
//@Entity(tableName = MoviesDbContract.Movies.TABLE_NAME)
//data class MovieEntity(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_ID)
//    val id: Long = 0,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_TITLE)
//    val title: String,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_OVERVIEW)
//    val overview: String,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_POSTER)
//    val poster: String,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_BACKDROP)
//    val backdrop: String,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_RATING)
//    val rating: Float,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_NUMBER_OF_REVIEWS)
//    val numberOfReviews: Int,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_ADULT)
//    val minimumAge: Int,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_RUNTIME)
//    val runtime: Int
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_GENRES)
//    val genres: List<Genre>,
//
//    @ColumnInfo(name = MoviesDbContract.Movies.COLUMN_NAME_ACTORS)
//    val actors: List<Actor>,
//)