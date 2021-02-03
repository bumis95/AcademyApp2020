package com.androidacademy.academyapp2020.repositories.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidacademy.academyapp2020.models.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ActorConverter::class, GenreConverter::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): MoviesDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                MoviesDbContract.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}