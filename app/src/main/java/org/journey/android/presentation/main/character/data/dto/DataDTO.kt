package org.journey.android.presentation.main.character.data.dto

import com.google.gson.annotations.SerializedName
import org.journey.android.domain.entity.CharacterEntity
import org.journey.android.domain.entity.CharacterInfoEntity

data class DataDTO(
    @SerializedName("characters")
    val characterDTOS: List<CharacterDTO>,
    @SerializedName("currentCharacter")
    val currentCharacterDTO: CurrentCharacterDTO,
    @SerializedName("currentSkin")
    val currentSkinDTO: CurrentSkinDTO,
    @SerializedName("skins")
    val skinDTOS: List<SkinDTO>
) {
    fun convertToCharacterInfoEntity(): CharacterInfoEntity {
        return CharacterInfoEntity(
            currentCharacterDTO,
            currentSkinDTO,
            characterDTOS.map { CharacterEntity(it.type, it.cardDTOS.map { it.convertToMohaengCharacterOptionEntity() }) },
            skinDTOS
        )
    }
}
