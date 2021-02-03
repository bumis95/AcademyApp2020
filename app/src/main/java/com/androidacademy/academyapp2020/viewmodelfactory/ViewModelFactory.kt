package com.androidacademy.academyapp2020.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidacademy.academyapp2020.data.MovieRepository
import com.androidacademy.academyapp2020.repositories.local.MoviesDatabase
import com.androidacademy.academyapp2020.repositories.network.RetrofitModule
import com.androidacademy.academyapp2020.views.ui.details.MovieDetailsViewModel
import com.androidacademy.academyapp2020.views.ui.movies.MoviesListViewModel

class ViewModelFactory(
    private val context: Context,
    private val repository: MovieRepository = MovieRepository(
        RetrofitModule.movieApiService,
        MoviesDatabase.getInstance(context.applicationContext)
    )
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            MoviesListViewModel::class.java -> {
                MoviesListViewModel(repository)
            }
            MovieDetailsViewModel::class.java -> {
                MovieDetailsViewModel(repository)
            }
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
}