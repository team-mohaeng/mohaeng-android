package org.journey.android.course.api

import org.journey.android.course.data.ResponseCourseData
import org.journey.android.course.data.ResponseLibraryData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CourseService {
    @GET("/api/challenges")
    fun getCourseData(
        @Header("Bearer") jwt : String
    ) : Call<ResponseCourseData>

    @GET("/api/courses")
    fun getLibraryData(
        @Header("Bearer") jwt : String
    ) : Call<ResponseLibraryData>
}