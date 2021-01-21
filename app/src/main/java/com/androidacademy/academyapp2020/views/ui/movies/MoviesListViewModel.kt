package com.androidacademy.academyapp2020.views.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.androidacademy.academyapp2020.data.MoviePagingSource
import com.androidacademy.academyapp2020.data.MovieRepository
import com.androidacademy.academyapp2020.models.Movie
import kotlinx.coroutines.flow.Flow

const val DEFAULT_PAGE_SIZE = 1

class MoviesListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val moviesList: Flow<PagingData<Movie>> =
        Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { MoviePagingSource(movieRepository) }
        )
            .flow.cachedIn(viewModelScope)

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE)
    }

    fun fetchMovies(): Flow<PagingData<Movie>> = moviesList
}