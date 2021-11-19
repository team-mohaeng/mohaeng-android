package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.character.data.entity.MohaengCharacterEntity

data class CharacterDTO(
    @SerializedName("cards")
    val cardDTOS: List<MohaengCharacterEntity>,
    @SerializedName("type")
    val type: Int
)