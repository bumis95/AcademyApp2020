package com.androidacademy.academyapp2020.data

import com.androidacademy.academyapp2020.models.Movie
import kotlinx.coroutines.flow.Flow

interface Repository

interface LocalRepository : Repository {

    suspend fun insertMoviesToDatabase(movies: List<Movie>)

    suspend fun getAllMoviesFromDatabase(): List<Movie>

    suspend fun getMovieFromDatabaseById(id: Int): Movie

    suspend fun deleteAllMoviesFromDatabase()
}

interface RemoteRepository : Repository {

    suspend fun loadMoviesList(page: Int): Flow<List<Movie>>

    suspend fun loadMovieDetails(movieId: Int): Movie
}