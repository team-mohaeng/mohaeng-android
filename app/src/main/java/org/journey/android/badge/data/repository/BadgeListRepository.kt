package org.journey.android.badge.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.badge.data.entity.BadgeEntity

interface BadgeListRepository {
    fun fetchBadgeList() : Single<List<BadgeEntity>>
}