package com.androidacademy.academyapp2020.data

import android.util.Log
import androidx.paging.PagingSource
import com.androidacademy.academyapp2020.models.Movie
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_PAGE_INDEX = 1

class MoviePagingSource(
    private val repository: MovieRepository,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = repository.loadMoviesList(page = page)
            Log.i("MoviePagingSource", page.toString())
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