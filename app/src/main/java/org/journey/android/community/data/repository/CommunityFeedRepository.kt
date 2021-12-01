package org.journey.android.community.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.community.data.entity.CommunityPostEntity

interface CommunityFeedRepository {
    fun fetchCommunityPost() : Single<List<CommunityPostEntity>>
}