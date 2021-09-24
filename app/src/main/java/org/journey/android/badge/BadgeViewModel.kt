package org.journey.android.badge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import javax.inject.Inject

@HiltViewModel
class BadgeViewModel @Inject constructor() : DisposableViewModel() {
    private val _badgeList = MutableLiveData<List<BadgeEntity>>()
    val badgeList : LiveData<List<BadgeEntity>>
        get() = _badgeList

    init{
        loadObtainedBadge()
    }

    private fun loadObtainedBadge(){
        val badgeList = listOf(
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            ),
            BadgeEntity(
                R.drawable.ic_badge,
                "초급 사진가"
            )
        )
        _badgeList.value = badgeList
    }

}