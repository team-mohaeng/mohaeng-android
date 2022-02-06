package org.journey.android.presentation.entry.login.data.response.email


data class ResponseEmailSignInDTO(
    val status: Int,
    val data: TokenDTO?
) {
    data class TokenDTO(
        val jwt: String
    )
}

