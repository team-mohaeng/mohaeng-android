package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName

data class RequestChangeCharacterDTO(
    @SerializedName("characterCard")
    val characterCard: Int,
    @SerializedName("characterSkin")
    val characterSkin: Int,
    @SerializedName("characterType")
    val characterType: Int
)