package org.journey.android.presentation.main.character.data.dto


import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("cards")
    val cardDTOS: List<CardDTO>,
    @SerializedName("type")
    val type: Int
)