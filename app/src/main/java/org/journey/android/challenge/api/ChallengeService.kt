package org.journey.android.challenge.api

import org.journey.android.challenge.data.ResponseChallengeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ChallengeService {
    @GET("/api/challenges/:courseId")
    fun getChallengeData(
        @Header("jwt") jwt : String
    ) : Call<ResponseChallengeData>
}