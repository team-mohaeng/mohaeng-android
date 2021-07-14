package org.journey.android.main

import okhttp3.OkHttpClient
import org.journey.android.data.RetrofitObjects
import org.journey.android.diary.DiaryPrivateService
import org.journey.android.diary.DiaryWriteService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL="http://3.36.55.247:5000"

    val retrofit : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val diaryWriteService: DiaryWriteService = retrofit.create(DiaryWriteService::class.java)
    val diaryPrivateService : DiaryPrivateService = retrofit.create(DiaryPrivateService::class.java)
}