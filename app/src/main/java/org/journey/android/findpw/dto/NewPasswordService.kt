package org.journey.android.findpw.dto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT

interface NewPasswordService {
    @PUT("/api/password")
    fun changePassword(
        @Body body: RequestNewPasswordData
    ): Call<ResponseNewPasswordData>
}