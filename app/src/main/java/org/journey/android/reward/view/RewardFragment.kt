package org.journey.android.reward.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.course.api.ServiceCreator
import org.journey.android.course.data.LibraryListInfo
import org.journey.android.course.data.ResponseLibraryData
import org.journey.android.course.view.LibraryListAdapter
import org.journey.android.databinding.FragmentRewardBinding
import org.journey.android.login.view.userJwt
import org.journey.android.main.RetrofitService
import org.journey.android.reward.data.ResponseRewardData
import org.journey.android.reward.dto.CompleteCourseData
import org.journey.android.reward.dto.RewardData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RewardFragment : BaseFragment<FragmentRewardBinding>() {
    private lateinit var rewardAdapter: RewardAdapter
    private lateinit var completeCourseAdapter: CompleteCourseAdapter
    private var datas = mutableListOf<CompleteCourseData>()
    private var lovePercent = ""
    private var successDay = ""

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRewardBinding {
        return FragmentRewardBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showReward()
        showCompleteCourse()
        //loadDatas()
        goBackMain()
    }

    private fun showReward() {
        rewardAdapter = RewardAdapter()
        binding.recyclerviewReward.adapter = rewardAdapter
        rewardAdapter.rewardList.addAll(
            listOf<RewardData>(
                RewardData(
                    percent = "27",
                    percentUnit = "%",
                    rewardContent = requireContext().getString(R.string.reward_challenge_percent)
                ),
                RewardData(
                    percent = "13",
                    percentUnit = "일",
                    rewardContent = requireContext().getString(R.string.reward_challenge_date)
                )
            )
        )
        rewardAdapter.notifyDataSetChanged()
    }


    private fun showCompleteCourse(){
        completeCourseAdapter = CompleteCourseAdapter()
        binding.recyclerviewShowReward.adapter = completeCourseAdapter
        completeCourseAdapter.completeCourseList.addAll(
            listOf<CompleteCourseData>(
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "뽀득뽀득 세균 퇴치",
                    courseComplete = "2021.07.16",
                    property = 0
                ),
                CompleteCourseData(
                    courseDate = "5일 코스",
                    courseName = "나는야 음유시인",
                    courseComplete = "2021.07.09",
                    property = 1
                ),
                CompleteCourseData(
                    courseDate = "3일 코스",
                    courseName = "추억을 더듬더듬",
                    courseComplete = "2021.07.04",
                    property = 2
                ),
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "상상만 해도 설레",
                    courseComplete = "2021.06.29",
                    property = 3
                ),
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "동네 한 바퀴",
                    courseComplete = "2021.06.11",
                    property = 1
                )
            )
        )
        completeCourseAdapter.notifyDataSetChanged()
    }


    private fun goBackMain() {
        binding.buttonPressedBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadDatas() {
        completeCourseAdapter = CompleteCourseAdapter()
        binding.recyclerviewShowReward.adapter = completeCourseAdapter

        RetrofitService.rewardService.getRewardData(
            userJwt
        ).enqueue(object : Callback<ResponseRewardData> {
            override fun onFailure(call: Call<ResponseRewardData>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<ResponseRewardData>,
                response: Response<ResponseRewardData>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (true) {
                        Log.d("서버 성공", "Reward 성공")
                        Log.d(
                            "서버", response.body()!!.data.toString()
                        )
                        lovePercent = response.body()!!.data!!.totalIncreasedAffinity.toString()
                        if (lovePercent.length == 1) {
                            lovePercent =
                                "0" + response.body()!!.data!!.totalIncreasedAffinity.toString()
                        }
                        successDay = response.body()!!.data!!.maxSuccessCount.toString()
                        if (successDay.length == 1) {
                            successDay = "0" + response.body()!!.data!!.maxSuccessCount.toString()
                        }

                        for (i in 0 until response.body()!!.data!!.courses.size) {
                            var property = response.body()!!.data!!.courses[i].property
                            var year =
                                response.body()!!.data!!.courses[i].challenges[response.body()!!.data!!.courses[i].challenges.size - 1].year
                            var month =
                                response.body()!!.data!!.courses[i].challenges[response.body()!!.data!!.courses[i].challenges.size - 1].month
                            var day =
                                response.body()!!.data!!.courses[i].challenges[response.body()!!.data!!.courses[i].challenges.size - 1].day
                            var date = year + "." + month + "." + day

                            datas = mutableListOf<CompleteCourseData>()
                            datas.apply {
                                add(
                                    CompleteCourseData(
                                        courseDate = response.body()!!.data!!.courses[i].totalDays.toString() + "일차",
                                        courseName = response.body()!!.data!!.courses[i].title.toString(),
                                        courseComplete = date,
                                        property = property
                                    )
                                )
                            }

//                            completeCourseAdapter.completeCourseList.addAll(
//                                datas
//                            )

                            // 데이터 변경되었으니 업데이트해라
                            completeCourseAdapter.notifyDataSetChanged()
                        }
                        showReward()
                    } else {
                        Log.d("서버 실패", "${response.body()}")
                    }
                }
            }
        })
    }
}