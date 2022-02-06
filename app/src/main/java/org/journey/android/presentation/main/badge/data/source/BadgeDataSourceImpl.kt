package org.journey.android.presentation.main.badge.data.source

import io.reactivex.rxjava3.core.Single
import org.journey.android.presentation.main.badge.data.dto.response.ResponseAchieveBadgeDTO
import org.journey.android.data.network.RetrofitInterface
import javax.inject.Inject

class BadgeDataSourceImpl @Inject constructor(
    private val retrofitInterface: RetrofitInterface
) : BadgeDataSource {
    override fun fetchAchieveBadgeList(): Single<ResponseAchieveBadgeDTO> =
        retrofitInterface.putAchieveBadgeList()
}