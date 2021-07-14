package org.journey.android.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.journey.android.base.BaseViewModel

class CommunityViewModel: BaseViewModel() {
    private val _title = MutableLiveData<Int>()
    val title: LiveData<Int>
        get() = _title
}