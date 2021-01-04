package com.androidacademy.academyapp2020.data.repository

import com.androidacademy.academyapp2020.data.entity.Movie

interface MovieRepository {
    suspend fun loadMoviesList(): List<Movie>
    suspend fun loadMovieDetails(movieId: Int): Movie
}