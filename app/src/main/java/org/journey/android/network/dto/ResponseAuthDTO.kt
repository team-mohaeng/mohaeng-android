package org.journey.android.network.dto

data class ResponseAuthDTO(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)