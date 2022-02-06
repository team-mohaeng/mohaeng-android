package org.journey.android.presentation.main.community.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.data.network.RetrofitInterface
import org.journey.android.data.network.qualifier.AuthRetrofitService
import javax.inject.Inject

class CommunityControllerImpl @Inject constructor(
    @AuthRetrofitService private val retrofitInterface: RetrofitInterface
) : CommunityController {
    override fun getCommunityFeed(): Single<ResponseCommunityFeedDTO> =
        retrofitInterface.getCommunityFeed()
}