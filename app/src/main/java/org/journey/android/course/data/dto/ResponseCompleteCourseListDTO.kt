package org.journey.android.course.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.course.data.dto.DataCourseDTO

data class ResponseCompleteCourseListDTO(
    @SerializedName("data")
    val dataCourseDTO: DataCourseDTO,
    @SerializedName("status")
    val status: Int,
    val message : String?
)