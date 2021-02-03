package com.androidacademy.academyapp2020.repositories.local

import android.provider.BaseColumns

object MoviesDbContract {
    const val DATABASE_NAME = "TheMovieDb.db"

    object Movies {
        const val TABLE_NAME = "movies"

        const val COLUMN_NAME_MOVIE_ID = BaseColumns._ID
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_POSTER = "poster"
        const val COLUMN_NAME_BACKDROP = "backdrop"
        const val COLUMN_NAME_RATING = "rating"
        const val COLUMN_NAME_NUMBER_OF_REVIEWS = "numberOfReviews"
        const val COLUMN_NAME_ADULT = "minimumAge"
        const val COLUMN_NAME_RUNTIME = "runtime"
        const val COLUMN_NAME_GENRES = "genres"
        const val COLUMN_NAME_ACTORS = "actors"
    }
}