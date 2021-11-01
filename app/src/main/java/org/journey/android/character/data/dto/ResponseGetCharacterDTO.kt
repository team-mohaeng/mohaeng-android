package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName

data class ResponseGetCharacterDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)