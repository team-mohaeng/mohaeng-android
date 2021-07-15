package org.journey.android.diary.service

import okhttp3.MultipartBody
import org.journey.android.diary.dto.ResponseDiaryWriteData
import retrofit2.Call
import retrofit2.http.*

interface DiaryWriteService {
    @Multipart
    @POST("/api/smallSatisfaction/write")
    fun writeDiary(
        @Header("Bearer") jwt: String,
        @Part ("content") content: String,
        @Part ("mainImage") mainImage: MultipartBody.Part,
        @Part ("mood") mood: String,
        @Part ("hashtags") hashtags: List<String>,
        @Part ("isPrivate") isPrivate: Boolean,

        ): Call<ResponseDiaryWriteData>
}