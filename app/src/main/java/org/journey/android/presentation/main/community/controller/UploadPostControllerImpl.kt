package org.journey.android.presentation.main.community.controller

import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import org.journey.android.presentation.main.community.data.dto.response.ResponsePostCommunityDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class UploadPostControllerImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : UploadPostController{
    override fun uploadContents(
        client: String,
        feed: MultipartBody.Part?,
        imageUrl: MultipartBody.Part?
    ): Single<ResponsePostCommunityDTO> =
        retrofitInterface.uploadPost(client, feed , imageUrl)
}