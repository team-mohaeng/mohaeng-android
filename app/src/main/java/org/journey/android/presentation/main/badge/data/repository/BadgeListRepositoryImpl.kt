package org.journey.android.presentation.main.badge.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.domain.entity.BadgeEntity
import org.journey.android.presentation.main.badge.data.source.BadgeDataSource
import javax.inject.Inject


class BadgeListRepositoryImpl @Inject constructor(
    private val badgeDataSource: BadgeDataSource
) : BadgeListRepository {
    override fun fetchBadgeList(id : Int): Single<List<BadgeEntity>> =
        badgeDataSource.fetchAchieveBadgeList().map { response->
            response.dataDTO.achieveBadgeDTOS.map {
                it.convertToAchieveBadgeEntity()
            }
        }
}