package org.journey.android.presentation.main.course


import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize
import org.journey.android.R

@Parcelize
enum class CourseCatalogEnum (@ColorRes val cardColor : Int, @ColorRes val buttonColor : Int, @DrawableRes val textBalloon : Int,@DrawableRes val cardStamp : Int, val subTitle : Int, val property : Int) {
    DeviateCourse(R.color.deviate_course1,R.color.deviate_course2, R.drawable.ic_balloon1, R.drawable.stamp1, R.string.deviate_course_period , 1 ),
    PromiseCourse(R.color.promise_course1,R.color.promise_course2, R.drawable.ic_balloon2, R.drawable.stamp2, R.string.promise_course_period , 2 ),
    CareCourse(R.color.care_course1,R.color.care_course2, R.drawable.ic_balloon3, R.drawable.stamp3, R.string.care_course_period , 3),
    HealthCourse(R.color.health_course1,R.color.health_course2, R.drawable.ic_balloon4, R.drawable.stamp4, R.string.health_course_period , 4 ),
    MemoryCourse(R.color.memory_course1,R.color.memory_course2, R.drawable.ic_balloon5, R.drawable.stamp5, R.string.memory_course_period , 5 ),
    SmartCourse(R.color.smart_course1,R.color.smart_course2, R.drawable.ic_balloon6, R.drawable.stamp6, R.string.smart_course_period , 6 ),
    LoveCourse(R.color.love_course1,R.color.love_course2, R.drawable.ic_balloon7, R.drawable.stamp7, R.string.love_course_period , 7 );

    companion object {
        fun checkCourseProperty(property: Int) : CourseCatalogEnum{
            return values().find {it.property == property} ?: throw IllegalArgumentException("error to check course property")
        }
    }
}