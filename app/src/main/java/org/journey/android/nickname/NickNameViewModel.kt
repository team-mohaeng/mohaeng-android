package org.journey.android.nickname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.base.DisposableViewModel
import javax.inject.Inject

@HiltViewModel
class NickNameViewModel @Inject constructor() : DisposableViewModel() {
    val nickname = MutableLiveData<String>()

    private val _nickNameStatus = MutableLiveData<NickNameStatus>()
    val nickNameStatus: LiveData<NickNameStatus>
        get() = _nickNameStatus

    fun checkNickNameAvailable(){
        nickname.value?.let { nickName ->
            if(nickName.length > 6) {
                _nickNameStatus.value = NickNameStatus.IS_NOT_AVAILABLE_NICKNAME
            }
            else {
            }
        }
    }

}