package com.androidacademy.academyapp2020.view.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidacademy.academyapp2020.data.entity.Movie
import com.androidacademy.academyapp2020.data.repository.MovieRepository
import com.androidacademy.academyapp2020.utils.LoadStatus
import kotlinx.coroutines.launch

class MoviesListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>>
        get() = _moviesList

    fun getMovies() {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.Loading
                _moviesList.value = movieRepository.loadMoviesList()
                _status.value = LoadStatus.Success
            } catch (exception: Exception) {
                _status.value = LoadStatus.Error
            }
        }
    }
}