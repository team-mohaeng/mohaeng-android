package org.journey.android.community.controller

import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.journey.android.community.data.dto.response.ResponsePostCommunityDTO
import org.journey.android.network.RetrofitInterface
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