package org.journey.android.presentation.main.community.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.community.data.dto.response.ResponseCommunityFeedDTO

interface CommunityController {
    fun getCommunityFeed() : Single<ResponseCommunityFeedDTO>
}