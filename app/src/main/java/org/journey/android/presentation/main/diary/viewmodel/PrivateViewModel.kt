package org.journey.android.presentation.main.diary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.journey.android.presentation.base.DisposableViewModel
import org.journey.android.presentation.main.community.controller.UploadPostController
import org.journey.android.presentation.main.community.data.dto.request.RequestUploadPostDTO
import org.journey.android.presentation.main.community.data.dto.response.ResponsePostCommunityDTO
import org.journey.android.presentation.preference.UserPreferenceManager
import org.journey.android.presentation.util.FormDataUtil
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PrivateViewModel @Inject constructor(
    private val userPreferenceManager: UserPreferenceManager,
    private val uploadPostController: UploadPostController
) : DisposableViewModel(){
    val hashMap = hashMapOf<String, RequestBody>()

    val content = MutableLiveData<String>()

    private val _isPrivate = MutableLiveData<Boolean>(false)
    val isPrivate: LiveData<Boolean>
        get() = _isPrivate

    private val _mood = MutableLiveData<Int>()
    val mood: LiveData<Int>
        get() = _mood

    private val _files = MutableLiveData<MutableList<File>?>(mutableListOf())
    val files: LiveData<MutableList<File>?>
        get() = _files

    private val _uploadImage = MutableLiveData<MultipartBody.Part?>()
    val uploadImage: LiveData<MultipartBody.Part?>
        get() = _uploadImage

    private val _uploadPost = MutableLiveData<List<RequestUploadPostDTO>>()
    val uploadPost: LiveData<List<RequestUploadPostDTO>>
        get() = _uploadPost

    private val _feedContents = MutableLiveData<MultipartBody.Part?>()
    val feedContents : LiveData<MultipartBody.Part?>
        get() = _feedContents

    private val _uploadSuccess = MutableLiveData<ResponsePostCommunityDTO>()
    val uploadSuccess : LiveData<ResponsePostCommunityDTO>
        get() = _uploadSuccess

    private val _presignedUrlList = MutableLiveData<List<String>?>(listOf())
    val presignedUrlList: LiveData<List<String>?>
        get() = _presignedUrlList

    fun changeMood(mood: Int) {
        _mood.value = mood
    }

    fun changeImage(image: MultipartBody.Part?) {
        _uploadImage.value = image
    }

    fun addFile(file: File?) {
        val fileList = files.value
        if (file != null) {
            fileList?.add(file)
        }
        _files.value = fileList
    }
    fun fetchPresignedUrl(urlList: List<String>) {
        _presignedUrlList.value = urlList
    }

    fun getJWT(): String {
        return userPreferenceManager.fetchUserAccessToken()
    }

    fun getNickname(): String {
        return userPreferenceManager.fetchUserNickName()
    }

    fun changeIsPrivate(isPrivate: Boolean) {
        _isPrivate.value = isPrivate
    }

    fun uploadImages() {
        val gson = Gson()
        val requestPost = RequestUploadPostDTO(
            content.value ?: "",
            isPrivate.value ?: false,
            mood.value ?: -1
        )
        val jsonPost = gson.toJson(requestPost)

        val requestBody = jsonPost.toRequestBody("application/json".toMediaTypeOrNull())

        val postPart: MultipartBody.Part = FormDataUtil.getBody("feed", jsonPost)

        addDisposable(
            uploadPostController.uploadContents(
                "aos",
                postPart,
                uploadImage.value
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("response", "$it")
                }, {
                    it.printStackTrace()
                })
        )
    }


}