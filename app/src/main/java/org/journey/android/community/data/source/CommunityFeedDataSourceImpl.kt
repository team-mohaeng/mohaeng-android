package org.journey.android.community.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class CommunityFeedDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : CommunityFeedDataSource{
    override fun fetchCommunityPost(): Single<ResponseCommunityFeedDTO> =
        retrofitInterface.getCommunityFeed()
}