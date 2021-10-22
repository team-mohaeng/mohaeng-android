package org.journey.android.signup.data.dto


import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("jwt")
    val jwt: String
)