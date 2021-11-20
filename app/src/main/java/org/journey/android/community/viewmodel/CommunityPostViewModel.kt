package org.journey.android.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.journey.android.base.DisposableViewModel
import org.journey.android.community.controller.UploadPostController
import org.journey.android.community.data.dto.request.RequestUploadPostDTO
import org.journey.android.community.data.dto.response.ResponseCommunityFeedDTO
import org.journey.android.community.data.dto.response.ResponsePostCommunityDTO
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

    private val _feedContents = MutableLiveData<ArrayList<RequestBody>>(arrayListOf())
    val feedContents : LiveData<ArrayList<RequestBody>>
        get() = _feedContents

    private val _uploadSuccess = MutableLiveData<ResponsePostCommunityDTO>()
    val uploadSuccess : LiveData<ResponsePostCommunityDTO>
        get() = _uploadSuccess



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

//    fun uploadPosting() {
//        addDisposable(
//            uploadPostController.uploadContents(
//                client = "aos",
//                _feedContents,
//                uploadImage.value
//            ).observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                       _uploadSuccess.postValue(it)
//                },{
//                    it.printStackTrace()
//                })
//        )
//    }


}