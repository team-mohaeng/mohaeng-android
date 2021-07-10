package org.journey.android.signup

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApiService {
    @POST("api/signup")
    fun signup(@Body body: RequestSignup): Single<ResponseSignup>
}