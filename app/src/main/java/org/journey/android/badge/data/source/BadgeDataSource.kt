package org.journey.android.badge.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.badge.data.dto.ResponseAchieveBadgeDTO

interface BadgeDataSource {
    fun fetchAchieveBadgeList() : Single<ResponseAchieveBadgeDTO>
}