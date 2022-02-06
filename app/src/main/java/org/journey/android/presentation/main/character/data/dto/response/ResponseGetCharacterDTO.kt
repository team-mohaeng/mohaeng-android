package org.journey.android.presentation.main.character.data.dto.response


import com.google.gson.annotations.SerializedName
import org.journey.android.presentation.main.character.data.dto.DataDTO
import org.journey.android.domain.entity.CharacterInfoEntity

data class ResponseGetCharacterDTO(
    @SerializedName("data")
    val dataDTO: DataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
) {
    fun convertToCharacterEntity(): CharacterInfoEntity {
        return dataDTO.convertToCharacterInfoEntity()
    }
}