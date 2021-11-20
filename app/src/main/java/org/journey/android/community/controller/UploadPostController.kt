package org.journey.android.community.controller

import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.journey.android.community.data.dto.response.ResponsePostCommunityDTO

interface UploadPostController {
    fun uploadContents(client : String, feed : ArrayList<RequestBody>,imageUrl : MultipartBody.Part?) :
            Single<ResponsePostCommunityDTO>
}