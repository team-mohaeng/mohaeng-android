package org.journey.android.reward.api

import org.journey.android.reward.data.ResponseRewardData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RewardService {
    @GET("/api/courses/complete")
    fun getRewardData(
        @Header("Bearer") jwt : String
    ) : Call<ResponseRewardData>
}