package org.journey.android.diary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MultipartBody
import org.journey.android.base.DisposableViewModel
import org.journey.android.community.controller.UploadPostController
import org.journey.android.community.data.dto.request.RequestUploadPostDTO
import org.journey.android.community.data.dto.response.ResponsePostCommunityDTO
import org.journey.android.preference.UserPreferenceManager
import javax.inject.Inject

@HiltViewModel
class PrivateViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager,
    private val uploadPostController: UploadPostController
) : DisposableViewModel(){

    private val _uploadImage = MutableLiveData<MultipartBody.Part?>()
    val uploadImage: LiveData<MultipartBody.Part?>
        get() = _uploadImage

    private val _feedContents = MutableLiveData<ArrayList<RequestUploadPostDTO>>(arrayListOf())
    val feedContents : LiveData<ArrayList<RequestUploadPostDTO>>
        get() = _feedContents

    private val _uploadSuccess = MutableLiveData<ResponsePostCommunityDTO>()
    val uploadSuccess : LiveData<ResponsePostCommunityDTO>
        get() = _uploadSuccess


    fun getJWT(): String {
        return userPreferenceManager.fetchUserAccessToken()
    }

    fun getNickname(): String {
        return userPreferenceManager.fetchUserNickName()
    }

//    fun uploadPosting() {
//        addDisposable(
//            uploadPostController.uploadContents(
//                client = "aos",
//                _feedContents,
//                _uploadImage
//            ).observeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    _uploadSuccess.postValue(it)
//                },{
//                    it.printStackTrace()
//                })
//        )
//    }

}