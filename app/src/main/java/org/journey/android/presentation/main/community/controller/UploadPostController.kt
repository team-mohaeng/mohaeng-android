package org.journey.android.presentation.main.community.controller

import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import org.journey.android.presentation.main.community.data.dto.response.ResponsePostCommunityDTO

interface UploadPostController {
    fun uploadContents(
        client: String,
        feed: MultipartBody.Part?,
        imageUrl: MultipartBody.Part?
    ) : Single<ResponsePostCommunityDTO>
}