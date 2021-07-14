package org.journey.android.diary

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Path

interface DiaryDeleteService {
    @DELETE("/api/smallSatisfaction/delete/{postId}")
    fun deleteDiary(
        @Path("postId") postId: Int,
        @Header("Bearer") jwt: String
    ): Call<Unit>
}