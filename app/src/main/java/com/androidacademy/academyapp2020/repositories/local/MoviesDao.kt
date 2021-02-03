package com.androidacademy.academyapp2020.repositories.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidacademy.academyapp2020.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE _id = :id")
    suspend fun getMovieById(id: Int): Movie

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}