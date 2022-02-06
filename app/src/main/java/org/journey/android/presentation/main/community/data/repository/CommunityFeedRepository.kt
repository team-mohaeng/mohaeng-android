package org.journey.android.presentation.main.community.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.domain.entity.CommunityPostEntity

interface CommunityFeedRepository {
    fun fetchCommunityPost() : Single<List<CommunityPostEntity>>
}