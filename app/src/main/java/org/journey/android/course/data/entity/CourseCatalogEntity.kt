package org.journey.android.course.data.entity

import androidx.annotation.ColorRes

data class CourseCatalogEntity(
    val courseProperty :Int,
    val courseName : String,
    val courseTotalDays : Int,
    val courseDescription : String,
    @ColorRes val courseBackGround : Int,
    @ColorRes val courseButtonColor : Int,
    val textBalloonImage : Int,
    val textBalloonText : Int,
    val courseStamp : Int
)