package org.journey.android.diary.service

import org.journey.android.diary.dto.RequestDiaryEmojiData
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface DiaryDislikeService {
    @PUT("/api/smallSatisfaction/unlike/{postId}")
    fun changeDislike(
        @Path("postId") postId: Int,
        @Header("Bearer") jwt : String
    ): Call<RequestDiaryEmojiData>
}