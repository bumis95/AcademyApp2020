package com.androidacademy.academyapp2020.data.model

data class Movie(
    val id: Int,
    val title: String,
//    val ageRate: Int,
//    val isLike: Int,
//    val imageUrl: Int,
//    val tag: Int,
//    val starRate: Int,
//    val numberOfReviews: Int,
//    val duration: Int,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float, // vote_average
    val numberOfRatings: Int, // vote_count
    val minimumAge: Int, // adult
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)
