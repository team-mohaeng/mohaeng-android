package org.journey.android.diary.service

import okhttp3.MultipartBody
import org.journey.android.diary.dto.RequestDiaryEmojiData
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

    @POST("/api/feed/{id}")
    fun reportDiary(
        @Path("id") id:Int,
        @Header("Content-Type") contenttype: String,
        @Header("Bearer") jwt: String,
        ): Call<Unit>

    @PUT("/api/feed/emoji/{id}")
    fun putEmoji(
        @Path("id") id:Int,
        @Header("Content-Type") contenttype: String,
        @Header("Bearer") jwt: String,
        @Body body : RequestDiaryEmojiData
    ): Call<Unit>

//    @DELETE("/api/feed/emoji/{id}")
//    fun deleteEmoji(
//        @Path("id") id:Int,
//        @Header("Content-Type") contenttype: String,
//        @Header("Bearer") jwt: String,
//        @Body body : RequestDiaryEmojiData
//    ): Call<Unit>

    @HTTP(method = "DELETE", path = "/api/feed/emoji/{id}", hasBody = true)
    fun deleteEmoji(
        @Path("id") id:Int,
        @Header("Content-Type") contenttype: String,
        @Header("Bearer") jwt: String,
        @Body body : RequestDiaryEmojiData
    ): Call<Unit>
}