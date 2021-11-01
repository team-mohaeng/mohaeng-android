package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName

data class ResponseChangeCharacterDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)