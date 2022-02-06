package org.journey.android.presentation.main.course.data.dto


import com.google.gson.annotations.SerializedName

data class DataCourseDTO(
    @SerializedName("courses")
    val cours: List<CourseDTO>,
    @SerializedName("isProgress")
    val isProgress: Boolean
)