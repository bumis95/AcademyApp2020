//package com.androidacademy.academyapp2020.repositories.local
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.androidacademy.academyapp2020.models.entities.MovieEntity
//
//@Database(entities = [MovieEntity::class], version = 1)
//abstract class MoviesDatabase : RoomDatabase() {
//
//    abstract val moviesDao: MoviesDao
//
//    companion object {
//        fun create(application: Context): MoviesDatabase =
//            Room.databaseBuilder(
//                application,
//                MoviesDatabase::class.java,
//                MoviesDbContract.DATABASE_NAME
//            )
//                .fallbackToDestructiveMigration()
//                .build()
//    }
//}