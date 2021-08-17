package org.journey.android.challenge.api

import org.journey.android.data.ResponseChallengeData
import org.journey.android.data.ResponseStampData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface ChallengeService {
    @GET("/api/challenges/{courseId}")
    fun getChallengeData(
        @Header("Bearer") jwt: String,
        @Path("courseId") courseId: Int
    ): Call<ResponseChallengeData>

    @PUT("/api/challenges/{courseId}/{challengeId}")
    fun putChallengeData(
        @Header("Bearer") jwt: String,
        @Path("courseId") courseId: Int,
        @Path("challengeId") challengeId: Int
    ): Call<ResponseStampData>
}