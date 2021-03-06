package org.journey.android.presentation.main.course.data.dto


import com.google.gson.annotations.SerializedName

data class CatalogDataDTO(
    @SerializedName("courses")
    val courses: List<CatalogCourseDTO>,
    @SerializedName("isProgress")
    val isProgress: Boolean
)