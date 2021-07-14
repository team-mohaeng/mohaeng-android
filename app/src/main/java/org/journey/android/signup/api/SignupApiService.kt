package org.journey.android.signup.api

import io.reactivex.Single
import org.journey.android.signup.data.RequestSignup
import org.journey.android.signup.data.ResponseSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApiService {
    @POST("/api/signup")
    fun signup(@Body body: RequestSignup): Call<ResponseSignup>
}