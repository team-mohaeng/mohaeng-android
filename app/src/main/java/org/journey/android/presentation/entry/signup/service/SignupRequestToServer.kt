package org.journey.android.presentation.entry.signup.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignupRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://mohaeng.me:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: SignupService = retrofit.create(SignupService::class.java)
}