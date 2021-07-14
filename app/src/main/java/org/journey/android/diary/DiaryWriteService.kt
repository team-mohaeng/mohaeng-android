package org.journey.android.diary

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DiaryWriteService {
    @POST("/api/smallSatisfaction/write")
    fun writeDiary(
        @Body body: RequestDiaryWriteData,
        @Header("Bearer") jwt : String
    ): Call<ResponseDiaryWriteData>
}