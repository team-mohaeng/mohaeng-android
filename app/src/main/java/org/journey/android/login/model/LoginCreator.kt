package org.journey.android.login.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginCreator {
    private const val BASE_URL = "http://3.36.55.247:5000"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val loginApiService: LoginApiService = retrofit.create(LoginApiService::class.java)
}