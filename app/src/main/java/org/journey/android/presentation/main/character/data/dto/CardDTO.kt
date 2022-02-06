package org.journey.android.presentation.main.character.data.dto


import org.journey.android.domain.entity.MohaengCharacterOptionEntity

data class CardDTO(
    val hasCard: Boolean,
    val id: Int,
    val image: String,
    val isNew: Boolean,
    val preview: String
){
    fun convertToMohaengCharacterOptionEntity(): MohaengCharacterOptionEntity {
        return MohaengCharacterOptionEntity(
            id,
            hasCard,
            isNew,
            image
        )
    }
}