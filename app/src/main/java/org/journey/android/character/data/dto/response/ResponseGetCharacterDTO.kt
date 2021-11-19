package org.journey.android.character.data.dto.response


import com.google.gson.annotations.SerializedName
import org.journey.android.character.data.dto.DataDTO
import org.journey.android.character.data.entity.CharacterInfoEntity

data class ResponseGetCharacterDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
) {
    fun convertToCharacterEntity(): CharacterInfoEntity {
        return CharacterInfoEntity(
            dataDTO.currentCharacterDTO,
            dataDTO.currentSkinDTO,
            dataDTO.characterDTOS,
            dataDTO.skinDTOS
        )
    }
}