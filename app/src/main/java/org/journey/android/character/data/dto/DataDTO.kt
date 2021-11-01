package org.journey.android.character.data.dto

import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("characters")
    val characterDTOS: List<CharacterDTO>,
    @SerializedName("currentCharacter")
    val currentCharacterDTO: CurrentCharacterDTO,
    @SerializedName("currentSkin")
    val currentSkinDTO: CurrentSkinDTO,
    @SerializedName("skins")
    val skinDTOS: List<SkinDTO>
)