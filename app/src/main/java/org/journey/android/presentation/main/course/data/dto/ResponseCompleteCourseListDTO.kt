package org.journey.android.presentation.main.course.data.dto


import com.google.gson.annotations.SerializedName

data class ResponseCompleteCourseListDTO(
    @SerializedName("data")
    val dataCourseDTO: DataCourseDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)