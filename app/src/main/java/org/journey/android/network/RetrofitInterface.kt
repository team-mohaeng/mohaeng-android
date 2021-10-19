package org.journey.android.network

import retrofit2.http.POST

interface RetrofitInterface {
    //서버나오면
    @POST("/api/kakao")
    fun kakaoLogin(
    )

}