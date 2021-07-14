package org.journey.android.main.model

import io.reactivex.Single
import org.journey.android.main.dto.ResponseMainModelItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MainApi {
    @GET("/api/home")
    fun getMainData(@Header("Bearer") jwt: String): Call<ResponseMainModelItem>
}