package com.androidacademy.academyapp2020.view.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.model.loadMovies
import kotlinx.coroutines.launch

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    init {
        viewModelScope.launch {
            _moviesList.value = loadMovies(application.applicationContext)
        }
    }
}