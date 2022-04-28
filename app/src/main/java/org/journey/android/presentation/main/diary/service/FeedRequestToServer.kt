package org.journey.android.presentation.main.diary.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FeedRequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.194.201:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: DiaryPrivateService = retrofit.create(DiaryPrivateService::class.java)
    var writeService: DiaryWriteService = retrofit.create(DiaryWriteService::class.java)
}