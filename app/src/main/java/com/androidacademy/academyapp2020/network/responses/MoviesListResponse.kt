package com.androidacademy.academyapp2020.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListResponse(
    @SerialName("results") val movieIdsList: List<MovieId>
)

@Serializable
data class MovieId(
    @SerialName("id") val id: Int
)