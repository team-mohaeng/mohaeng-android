package org.journey.android.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.base.DisposableViewModel
import org.journey.android.community.controller.CommunityController
import org.journey.android.community.data.dto.ResponseCommunityFeedDTO
import org.journey.android.community.data.entity.CommunityPostEntity
import org.journey.android.community.data.repository.CommunityFeedRepository
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor(
    private val communityController: CommunityController,
    private val communityFeedRepository: CommunityFeedRepository
): DisposableViewModel() {
    private val _communityPostList = MutableLiveData<List<CommunityPostEntity>>()
    val communityPostList : LiveData<List<CommunityPostEntity>>
        get() = _communityPostList

    private val _getEntireCommuntiy = MutableLiveData<ResponseCommunityFeedDTO.Data>()
    val getEntireCommunity : LiveData<ResponseCommunityFeedDTO.Data>
        get() = _getEntireCommuntiy

    fun fetchCommunityPost() {
        addDisposable(
            communityFeedRepository.fetchCommunityPost()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _communityPostList.postValue(it)
                },{
                    it.printStackTrace()
                })
        )
    }

}