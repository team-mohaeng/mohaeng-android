package org.journey.android.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.journey.android.R
import org.journey.android.base.DisposableViewModel
import org.journey.android.community.CommunityPostEntity
import javax.inject.Inject

@HiltViewModel
class CommunityPostViewModel @Inject constructor()  : DisposableViewModel() {
    private val _communityPostList = MutableLiveData<List<CommunityPostEntity>>()
    val communityPostList : LiveData<List<CommunityPostEntity>>
        get() = _communityPostList

    init{
        fetchPostList()
    }

    private fun fetchPostList(){
        val communityPostList = listOf(
            CommunityPostEntity(
                R.drawable.ic_frame_229,
                "냄새나니까 좀 씻어 챌린지 6일차",
                "씻어당장씻어빨리씻어뽀득뽀득",
                "주예",
                "9월 15일"
            ),
            CommunityPostEntity(
                R.drawable.ic_img_null,
                "뽀득뽀득 세균 퇴치 3일차",
                "어쩌라고저쩌라고살찌라고살빽라",
                "안드짱",
                "2월 18일"
            ),
            CommunityPostEntity(
                R.drawable.ic_frame_229,
                "어쩌구 모행 챌린지 5일차",
                "너도하고싶지?메롱ㅋ",
                "아랑",
                "10월 04일"
            ),
            CommunityPostEntity(
                R.drawable.ic_img_null,
                "인생힘들어도 밥은잘먹더라 챌린지 1일차",
                "어떡하냐?ㅋㅋ아ㅋㅋ배고파",
                "모행",
                "6월 06일"
            )
        )
        _communityPostList.value = communityPostList
    }
}