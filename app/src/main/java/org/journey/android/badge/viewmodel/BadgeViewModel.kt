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
import org.journey.android.character.data.entity.MohaengCharacterEntity
import javax.inject.Inject

@HiltViewModel
class BadgeViewModel @Inject constructor(
    private val badgeController: BadgeController
) : DisposableViewModel() {
    private val _getBadgeList = MutableLiveData<Boolean>()

    private val _badgeId = MutableLiveData<Int>()
    val badgeId: LiveData<Int>
        get() = _badgeId

    private val _badgeList = MutableLiveData<List<BadgeEntity>>()
    val badgeList : LiveData<List<BadgeEntity>>
        get() = _badgeList

    fun changeBadgeId(id: Int) {
        _badgeId.value = id
    }

    fun pickPlace() {
        badgeId.value?.let {
            addDisposable(
                badgeController.putBadgeList(
                    _badgeId.value!!
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                    }, {
                        it.printStackTrace()
                    })
            )
        }
    }

    fun loadObtainedBadge(id : Int){
       addDisposable(
           badgeController.putBadgeList(
                id
           )
               .observeOn(Schedulers.io())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe({
                   _badgeList.postValue(it.dataDTO.achieveBadgeDTOS.map { it.convertToAchieveBadgeEntity() })
                   _getBadgeList.postValue(true)
               },{
                   it.printStackTrace()
               })
       )
    }
}