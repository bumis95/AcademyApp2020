package com.androidacademy.academyapp2020.data

import android.util.Log
import com.androidacademy.academyapp2020.models.Movie
import com.androidacademy.academyapp2020.repositories.local.MoviesDatabase
import com.androidacademy.academyapp2020.repositories.network.TmbdApiService
import com.androidacademy.academyapp2020.repositories.network.responses.MovieId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MovieRepository(
    private val apiService: TmbdApiService,
    private val database: MoviesDatabase
) : LocalRepository, RemoteRepository {

    private var isCached = true

    override suspend fun insertMoviesToDatabase(movies: List<Movie>) =
        withContext(Dispatchers.IO) {
            Log.d(REPOSITORY_TAG, "Add $movies in DB")
            database.getMoviesDao().insertMovies(movies)
        }

    override suspend fun getAllMoviesFromDatabase(): List<Movie> =
        withContext(Dispatchers.IO) {
            val movies = database.getMoviesDao().getMovies()
            Log.d(REPOSITORY_TAG, "Get $movies from DB")
            return@withContext movies
        }

    override suspend fun getMovieFromDatabaseById(id: Int): Movie =
        withContext(Dispatchers.IO) {
            Log.d(REPOSITORY_TAG, "Get movie by id=$id from DB")
            database.getMoviesDao().getMovieById(id)
        }

    override suspend fun deleteAllMoviesFromDatabase() =
        withContext(Dispatchers.IO) {
            Log.d(REPOSITORY_TAG, "Delete all movies from DB")
            database.getMoviesDao().clearMovies()
        }

    override suspend fun loadMoviesList(page: Int): Flow<List<Movie>> = flow {
        val idsList: List<MovieId>
        val moviesList = mutableListOf<Movie>()
        try {
            if (isCached) {
                val db: List<Movie> = getAllMoviesFromDatabase()
                isCached = false
                Log.d(REPOSITORY_TAG, "$isCached")
                emit(db)
            } else {
                idsList = apiService.getPopularMovies(page).movieIdsList
                idsList.forEach {
                    val movie = loadMovieDetails(it.id)
                    Log.d(REPOSITORY_TAG, movie.toString())
                    moviesList.add(movie)
                }
                emit(moviesList)
                insertMoviesToDatabase(moviesList)
            }
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Error while loading movies list (page=${page}): $e")
        }
    }

    override suspend fun loadMovieDetails(movieId: Int): Movie =
        if (isCached) {
            getMovieFromDatabaseById(movieId)
        } else
            apiService.getMovieDetailsById(movieId).toMovie()

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 1
        const val REPOSITORY_TAG = "REPOSITORY_TAG"
    }
}