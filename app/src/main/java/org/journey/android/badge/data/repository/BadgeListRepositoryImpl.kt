package org.journey.android.badge.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.badge.data.entity.BadgeEntity
import org.journey.android.badge.data.source.BadgeDataSource
import javax.inject.Inject


class BadgeListRepositoryImpl @Inject constructor(
    private val badgeDataSource: BadgeDataSource
) : BadgeListRepository {
    override fun fetchBadgeList(): Single<List<BadgeEntity>> =
        badgeDataSource.fetchAchieveBadgeList().map { response->
            response.dataDTO.achieveBadgeDTOS.map {
                it.convertToAchieveBadgeEntity()
            }
        }
}