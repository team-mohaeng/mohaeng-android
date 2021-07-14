package org.journey.android.course.api

import org.journey.android.course.data.ResponseChooseData
import org.journey.android.course.data.ResponseLibraryData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface CourseChooseService {
    @PUT("/api/courses/{id}")
    fun putCourseData(
        @Header("Bearer") jwt : String,
        @Path("id") id : Int
    ) : Call<ResponseChooseData>
}