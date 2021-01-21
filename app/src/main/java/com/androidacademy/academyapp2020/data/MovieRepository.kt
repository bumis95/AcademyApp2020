package com.androidacademy.academyapp2020.data

import android.util.Log
import com.androidacademy.academyapp2020.models.Movie
import com.androidacademy.academyapp2020.repositories.network.TmbdApiService
import com.androidacademy.academyapp2020.repositories.network.responses.MovieId

class MovieRepository(private val service: TmbdApiService) {

    suspend fun loadMoviesList(page: Int): List<Movie> {
        val idsList: List<MovieId>
        val moviesList = mutableListOf<Movie>()
        try {
            idsList = service.getPopularMovies(page).movieIdsList
            idsList.forEach {
                val movie = loadMovieDetails(it.id)
                Log.i("MovieRepository", movie.toString())
                moviesList.add(movie)
            }
        } catch (e: Exception) {
            Log.i("MovieRepository", "Error while loading movies list (page=${page}): $e")
        }
        return moviesList
    }

    suspend fun loadMovieDetails(movieId: Int): Movie =
        service.getMovieDetailsById(movieId).toMovie()
}