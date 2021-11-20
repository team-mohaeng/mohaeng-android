package org.journey.android.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.journey.android.base.DisposableViewModel
import org.journey.android.community.controller.CommunityController
import org.journey.android.community.controller.UploadPostController
import org.journey.android.community.data.dto.response.RequestUploadPostDTO
import org.journey.android.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.community.data.entity.CommunityPostEntity
import org.journey.android.community.data.repository.CommunityFeedRepository
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor(
    private val communityFeedRepository: CommunityFeedRepository,
    private val uploadPostController: UploadPostController
): DisposableViewModel() {
    private val _communityPostList = MutableLiveData<List<CommunityPostEntity>>()
    val communityPostList : LiveData<List<CommunityPostEntity>>
        get() = _communityPostList

    private val _getEntireCommuntiy = MutableLiveData<ResponseCommunityFeedDTO.Data>()
    val getEntireCommunity : LiveData<ResponseCommunityFeedDTO.Data>
        get() = _getEntireCommuntiy

    private val _uploadImage = MutableLiveData<MultipartBody.Part?>()
    val uploadImage: LiveData<MultipartBody.Part?>
        get() = _uploadImage

    private val _feedContents = MutableLiveData<ArrayList<RequestUploadPostDTO>>(arrayListOf())
    val feedContents : LiveData<ArrayList<RequestUploadPostDTO>>
        get() = _feedContents



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