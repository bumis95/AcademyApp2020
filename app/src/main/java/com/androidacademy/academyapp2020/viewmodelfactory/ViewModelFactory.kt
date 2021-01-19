package com.androidacademy.academyapp2020.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidacademy.academyapp2020.data.repositories.MovieRepository
import com.androidacademy.academyapp2020.views.ui.details.MovieDetailsViewModel
import com.androidacademy.academyapp2020.views.ui.movies.MoviesListViewModel

class ViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            MoviesListViewModel::class.java -> MoviesListViewModel(movieRepository)
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(movieRepository)
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
}