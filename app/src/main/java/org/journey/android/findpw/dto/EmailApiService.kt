package org.journey.android.findpw.dto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface EmailApiService {
    @GET("/api/password/{userId}")
    fun findPW(
        @Path("userId") userId: String
    ): Call<ResponsePasswordData>
}