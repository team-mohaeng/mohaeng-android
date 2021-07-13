package org.journey.android.course.api

import org.journey.android.course.data.ResponseCourseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseService {
    @GET("/api/challenges")
    fun getChallengeData(
        @Header("jwt") jwt : String
    ) : Call<ResponseCourseData>
}