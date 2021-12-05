package org.journey.android.character.data.dto


import com.google.gson.annotations.SerializedName

data class SkinDTO(
    @SerializedName("hasSkin")
    val hasSkin: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
){

}