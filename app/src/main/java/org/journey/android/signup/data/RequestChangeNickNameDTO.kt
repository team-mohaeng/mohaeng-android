package org.journey.android.signup.data


import com.google.gson.annotations.SerializedName

data class RequestChangeNickNameDTO(
    @SerializedName("nickname")
    val nickname: String
)