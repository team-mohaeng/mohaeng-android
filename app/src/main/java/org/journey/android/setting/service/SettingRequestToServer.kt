package org.journey.android.setting.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SettingRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://54.180.103.98:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: SettingService = retrofit.create(SettingService::class.java)
}