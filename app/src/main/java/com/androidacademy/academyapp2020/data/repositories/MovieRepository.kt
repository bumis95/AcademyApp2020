package com.androidacademy.academyapp2020.data.repositories

import com.androidacademy.academyapp2020.data.entities.Movie

interface MovieRepository {
    suspend fun loadMoviesList(page: Int): List<Movie>
    suspend fun loadMovieDetails(movieId: Int): Movie
}