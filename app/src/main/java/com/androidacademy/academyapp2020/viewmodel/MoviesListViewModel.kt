package com.androidacademy.academyapp2020.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.repository.IRepository
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: IRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun getMovies(context: Context) {
        viewModelScope.launch {
            _moviesList.value = repository.loadMoviesFromRepository(context)
        }
    }
}