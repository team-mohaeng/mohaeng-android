package org.journey.android.diary

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface DiaryLikeService {
    @PUT("/api/smallSatisfaction/like/{postId}")
    fun changeLike(
        @Path("postId") postId: Int,
        @Header("Bearer") jwt : String
    ): Call<ResponseDiaryLikeData>
}