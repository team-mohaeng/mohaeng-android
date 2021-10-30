package org.journey.android.badge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.R
import org.journey.android.badge.controller.BadgeController
import org.journey.android.badge.data.entity.BadgeEntity
import org.journey.android.badge.data.repository.BadgeListRepository
import org.journey.android.base.DisposableViewModel
import javax.inject.Inject

@HiltViewModel
class BadgeViewModel @Inject constructor(
    private val badgeController: BadgeController,
    private val badgeListRepository: BadgeListRepository
) : DisposableViewModel() {
    private val _badgeList = MutableLiveData<List<BadgeEntity>>()
    val badgeList : LiveData<List<BadgeEntity>>
        get() = _badgeList



    fun loadObtainedBadge(){
       addDisposable(
           badgeListRepository.fetchBadgeList()
               .observeOn(Schedulers.io())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe({
                   _badgeList.postValue(it)
               },{
                   it.printStackTrace()
               })
       )
    }

}