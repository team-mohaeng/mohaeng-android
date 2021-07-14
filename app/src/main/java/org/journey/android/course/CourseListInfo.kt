package org.journey.android.course

data class CourseListInfo(
    val courseDay: String,
    val courseContent: String,
    val courseComplete: String,
    val courseCurrent: Boolean,
    val type : Int,
    val property : Int
)
