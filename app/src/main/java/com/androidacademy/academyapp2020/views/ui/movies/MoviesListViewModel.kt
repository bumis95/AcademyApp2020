package com.androidacademy.academyapp2020.views.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androidacademy.academyapp2020.data.MoviePagingSource
import com.androidacademy.academyapp2020.data.MovieRepository
import com.androidacademy.academyapp2020.data.MovieRepository.Companion.DEFAULT_PAGE_SIZE
import com.androidacademy.academyapp2020.models.Movie
import kotlinx.coroutines.flow.Flow

class MoviesListViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val movies: Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(repository) }
        )
            .flow.cachedIn(viewModelScope)

    fun fetchMovies(): Flow<PagingData<Movie>> = movies
}