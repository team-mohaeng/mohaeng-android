package org.journey.android.community.dto

import org.journey.android.community.dto.ResponseCommunityData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CommunityService {
    @GET("/api/smallSatisfaction/community/{sort}")
    fun getCommunityDiary(
        @Path("sort") sort: String,
        @Header("Bearer") jwt: String
    ): Call<ResponseCommunityData>
}