package org.journey.android.presentation.main.community.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class CommunityFeedDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : CommunityFeedDataSource{
    override fun fetchCommunityPost(): Single<ResponseCommunityFeedDTO> =
        retrofitInterface.getCommunityFeed()
}