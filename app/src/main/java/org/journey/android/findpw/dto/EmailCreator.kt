package org.journey.android.findpw.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EmailCreator {
    private const val BASE_URL = "http://3.36.55.247:5000"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(EmailCreator.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val emailApiService: EmailApiService = retrofit.create(EmailApiService::class.java)
    val newPasswordService : NewPasswordService = retrofit.create(NewPasswordService::class.java)
}