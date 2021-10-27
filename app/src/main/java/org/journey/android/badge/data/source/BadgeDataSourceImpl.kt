package org.journey.android.badge.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.badge.data.dto.ResponseAchieveBadgeDTO
import org.journey.android.badge.data.source.BadgeDataSource
import org.journey.android.network.RetrofitInterface
import javax.inject.Inject

class BadgeDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : BadgeDataSource {
    override fun fetchAchieveBadgeList(): Single<ResponseAchieveBadgeDTO> =
        retrofitInterface.putAchieveBadgeList()
}