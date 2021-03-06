package org.journey.android.presentation.entry.signup.service

import org.journey.android.presentation.entry.signup.data.request.RequestEmail
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SignupService {
    @POST("/api/email")
    fun checkEmail(
        @Header("Content-Type") contenttype: String,
        @Body body: RequestEmail
    ): Call<Unit>
}