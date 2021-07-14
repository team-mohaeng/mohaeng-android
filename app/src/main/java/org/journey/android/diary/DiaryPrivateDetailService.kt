package org.journey.android.diary

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DiaryPrivateDetailService {
    @GET("/api/smallSatisfaction/detail/{postId}")
    fun getPrivateDetailDiary(
        @Path("postId") postId: Int,
        @Header("Bearer") jwt : String
    ) : Call<ResponseDiaryPrivateDetailData>
}