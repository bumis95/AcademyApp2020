//package com.androidacademy.academyapp2020.repositories.local
//
//import androidx.paging.PagingSource
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.androidacademy.academyapp2020.models.Movie
//
//@Dao
//interface MoviesDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(movies: List<Movie>)
//
//    @Query("SELECT * FROM movies WHERE title LIKE :query")
//    fun pagingSource(query: String): PagingSource<Int, Movie>
//
//    @Query("DELETE FROM movies")
//    suspend fun clearAll()
//}