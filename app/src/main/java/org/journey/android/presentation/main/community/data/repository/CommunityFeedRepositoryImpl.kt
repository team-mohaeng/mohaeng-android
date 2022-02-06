package org.journey.android.presentation.main.community.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.domain.entity.CommunityPostEntity
import org.journey.android.presentation.main.community.data.source.CommunityFeedDataSource
import javax.inject.Inject

class CommunityFeedRepositoryImpl @Inject constructor(
    private val communityFeedDataSource: CommunityFeedDataSource) :
CommunityFeedRepository{
    override fun fetchCommunityPost(): Single<List<CommunityPostEntity>> =
        communityFeedDataSource.fetchCommunityPost().map { response->
            response.data.feeds.map { it.convertToCommunityPostEntity() }
        }
}