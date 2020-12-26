package com.androidacademy.academyapp2020.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.repository.IRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: IRepository) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getMovie(context: Context, movieId: Int?) {
        viewModelScope.launch {
            _movie.value = repository.loadMovieFromRepository(context, movieId)
        }
    }
}