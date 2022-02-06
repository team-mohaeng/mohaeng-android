package org.journey.android.presentation.main.community.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.community.data.dto.response.ResponseCommunityFeedDTO

interface CommunityFeedDataSource {
    fun fetchCommunityPost() : Single<ResponseCommunityFeedDTO>
}