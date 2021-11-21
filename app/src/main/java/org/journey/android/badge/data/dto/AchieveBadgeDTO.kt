package org.journey.android.badge.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.badge.data.entity.BadgeEntity

data class AchieveBadgeDTO(
    @SerializedName("hasBadge")
    val hasBadge: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String?,
    @SerializedName("info")
    val info: String,
    @SerializedName("name")
    val name: String
){
    fun convertToAchieveBadgeEntity() : BadgeEntity {
        return BadgeEntity(
            id,
            image,
            name
        )
    }
}