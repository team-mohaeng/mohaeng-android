package org.journey.android.network

import io.reactivex.rxjava3.core.Single
import org.journey.android.main.dto.ResponseBeforeChallengeDTO
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    //서버나오면
    @POST("/api/kakao")
    fun kakaoLogin(
    )

    @GET("/api/home")
    fun getHomeResource(
        @Query("Bearer") bearer : List<String>
    ) : Single<ResponseBeforeChallengeDTO>

}