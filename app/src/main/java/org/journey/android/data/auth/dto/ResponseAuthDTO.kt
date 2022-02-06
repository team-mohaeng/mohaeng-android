package org.journey.android.data.auth.dto

data class ResponseAuthDTO(
    val accessToken: String,
    val refreshToken: String,
    val userEmail: String,
    val userId: String
)