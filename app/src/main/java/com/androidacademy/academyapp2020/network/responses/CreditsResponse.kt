package com.androidacademy.academyapp2020.network.responses

import com.androidacademy.academyapp2020.data.entities.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast") val castList: List<Actor>
)