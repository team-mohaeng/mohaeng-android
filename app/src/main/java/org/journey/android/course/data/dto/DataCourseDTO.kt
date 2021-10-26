package org.journey.android.course.data.dto


import com.google.gson.annotations.SerializedName
import org.journey.android.course.data.dto.CourseDTO

data class DataCourseDTO(
    @SerializedName("courses")
    val cours: List<CourseDTO>,
    @SerializedName("isProgress")
    val isProgress: Boolean
)