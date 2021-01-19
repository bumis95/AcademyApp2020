package com.androidacademy.academyapp2020.data.repositories

import android.util.Log
import com.androidacademy.academyapp2020.data.entities.Movie
import com.androidacademy.academyapp2020.network.MovieApiService
import com.androidacademy.academyapp2020.network.responses.MovieId

private const val REPOSITORY_TAG = "repository_tag"

class MovieRepositoryImpl(private val movieApiService: MovieApiService) : MovieRepository {

    override suspend fun loadMoviesList(page: Int): List<Movie> {
        val idsList: List<MovieId>
        val moviesList = mutableListOf<Movie>()
        try {
            idsList = movieApiService.getPopularMovies(page).movieIdsList
            idsList.forEach {
                val movie = loadMovieDetails(it.id)
                Log.i(REPOSITORY_TAG, movie.toString())
                moviesList.add(movie)
            }
        } catch (e: Exception) {
            Log.i(REPOSITORY_TAG, e.toString())
        }
        return moviesList
    }

    override suspend fun loadMovieDetails(movieId: Int): Movie =
        movieApiService.getMovieDetailsById(movieId).toMovie()
}