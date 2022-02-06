package org.journey.android.presentation.main.badge.controller

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.badge.data.dto.response.ResponseAchieveBadgeDTO

interface BadgeController {
    fun putBadgeList(id : Int) : Single<ResponseAchieveBadgeDTO>
}