package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName

data class CardDTO(
    @SerializedName("hasCard")
    val hasCard: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isNew")
    val isNew: Boolean,
    @SerializedName("preview")
    val preview: String
)