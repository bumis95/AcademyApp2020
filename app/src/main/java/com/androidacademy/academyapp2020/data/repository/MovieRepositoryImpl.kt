package com.androidacademy.academyapp2020.data.repository

import android.util.Log
import com.androidacademy.academyapp2020.data.entity.Movie
import com.androidacademy.academyapp2020.network.RetrofitModule
import com.androidacademy.academyapp2020.network.response.MovieId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val REPOSITORY_TAG = "repository_tag"

class MovieRepositoryImpl : MovieRepository {

    private val movieApiService = RetrofitModule.movieApiService

    override suspend fun loadMoviesList(): List<Movie> = withContext(Dispatchers.IO) {
        val idsList: List<MovieId>
        val moviesList = mutableListOf<Movie>()
        try {
            idsList = movieApiService.getPopularMovies().movieIdsList
            idsList.forEach {
                val movie = loadMovieDetails(it.id)
                Log.i(REPOSITORY_TAG, movie.toString())
                moviesList.add(movie)
            }
        } catch (e: Exception) {
            Log.i(REPOSITORY_TAG, e.toString())
        }
        return@withContext moviesList
    }

    override suspend fun loadMovieDetails(movieId: Int): Movie = withContext(Dispatchers.IO) {
        return@withContext movieApiService.getMovieDetailsById(movieId).toMovie()
    }
}