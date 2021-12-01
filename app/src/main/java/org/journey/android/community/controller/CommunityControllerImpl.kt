package org.journey.android.community.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.network.RetrofitInterface
import org.journey.android.qualifier.AuthRetrofitService
import javax.inject.Inject

class CommunityControllerImpl @Inject constructor(
    @AuthRetrofitService private val retrofitInterface: RetrofitInterface
) : CommunityController {
    override fun getCommunityFeed(): Single<ResponseCommunityFeedDTO> =
        retrofitInterface.getCommunityFeed()
}