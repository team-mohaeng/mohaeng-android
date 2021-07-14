package org.journey.android.diary

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DiaryPrivateService {
    @GET("/api/smallSatisfaction/myDrawer/{year}/{month}")
    fun getPrivateDiary(
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Header("Bearer") jwt : String
    ) : Call<ResponseDiaryPrivateData>
}