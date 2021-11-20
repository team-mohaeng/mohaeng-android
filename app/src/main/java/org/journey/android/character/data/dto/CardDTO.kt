package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.character.data.entity.MohaengCharacterOptionEntity

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