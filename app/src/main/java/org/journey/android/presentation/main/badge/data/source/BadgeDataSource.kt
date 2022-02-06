package org.journey.android.presentation.main.badge.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.badge.data.dto.response.ResponseAchieveBadgeDTO

interface BadgeDataSource {
    fun fetchAchieveBadgeList() : Single<ResponseAchieveBadgeDTO>
}