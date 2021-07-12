package org.journey.android.data

import retrofit2.http.GET
import retrofit2.http.Header

interface MainService {
    @GET("api/home")
    fun mainViewRetrofit(@Header("jwt") jwt : String) : MainService
}