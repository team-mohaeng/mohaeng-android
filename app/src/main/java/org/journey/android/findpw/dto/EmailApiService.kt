package org.journey.android.findpw.dto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface EmailApiService {
    @GET("/api/password/{userId}")
    fun findPW(@Body body: RequestPasswordData): Call<ResponsePasswordData>
}