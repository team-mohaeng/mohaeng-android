package org.journey.android.community

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CommunityService {
    @GET("/api/smallSatisfaction/community/{sort}")
    fun getCommunityDiary(
        @Path("sort") sort: Int,
        @Header("Bearer") jwt: String
    ): Call<ResponseCommunityData>
}