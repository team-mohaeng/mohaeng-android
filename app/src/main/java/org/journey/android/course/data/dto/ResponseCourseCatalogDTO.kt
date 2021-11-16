package org.journey.android.course.data.dto


import com.google.gson.annotations.SerializedName

data class ResponseCourseCatalogDTO(
    @SerializedName("data")
    val catalogDataDTO: CatalogDataDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)