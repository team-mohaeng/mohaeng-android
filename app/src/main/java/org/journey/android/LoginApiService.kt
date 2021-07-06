package org.journey.android

import io.reactivex.Single
import org.journey.android.login.RequestLogin
import org.journey.android.login.ResponseLogin
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login/signin")
    fun login(@Body body: RequestLogin): Single<ResponseLogin>
}