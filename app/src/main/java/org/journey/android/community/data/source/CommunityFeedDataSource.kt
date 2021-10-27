package org.journey.android.community.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.community.data.dto.ResponseCommunityFeedDTO

interface CommunityFeedDataSource {
    fun fetchCommunityPost() : Single<ResponseCommunityFeedDTO>
}