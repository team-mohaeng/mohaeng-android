package org.journey.android.presentation.main.badge.data.repository

import io.reactivex.rxjava3.core.Single
import org.journey.android.domain.entity.BadgeEntity

interface BadgeListRepository {
    fun fetchBadgeList(id : Int) : Single<List<BadgeEntity>>
}