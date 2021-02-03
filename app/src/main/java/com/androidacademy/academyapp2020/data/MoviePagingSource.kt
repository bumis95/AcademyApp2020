package com.androidacademy.academyapp2020.data

import android.util.Log
import androidx.paging.PagingSource
import com.androidacademy.academyapp2020.data.MovieRepository.Companion.DEFAULT_PAGE_INDEX
import com.androidacademy.academyapp2020.data.MovieRepository.Companion.REPOSITORY_TAG
import com.androidacademy.academyapp2020.models.Movie
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val repository: MovieRepository,
) : PagingSource<Int, Movie>() {

    @FlowPreview
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        Log.d(REPOSITORY_TAG, "Page=$page")
        return try {
            val response = repository.loadMoviesList(page).flatMapConcat { it.asFlow() }.toList()
            LoadResult.Page(
                data = response,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page.dec(),
                nextKey = if (response.isEmpty()) null else page.inc()
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}