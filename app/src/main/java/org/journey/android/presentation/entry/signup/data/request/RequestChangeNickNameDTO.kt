package org.journey.android.presentation.entry.signup.data.request


import com.google.gson.annotations.SerializedName

data class RequestChangeNickNameDTO(
    @SerializedName("nickname")
    val nickname: String
)