package org.journey.android.login.model

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("/api/signin")
    fun login(@Body body: RequestLogin): Call<ResponseLogin>
}