package org.journey.android.main

import io.reactivex.Single
import org.journey.android.main.dto.ResponseMainModelItem
import retrofit2.http.GET
import retrofit2.http.Header

interface MainApi {
    @GET("/api/home")
    fun getChallengeList(@Header("Bearer") jwt: String): Single<ResponseMainModelItem>
}