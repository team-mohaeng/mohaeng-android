package org.journey.android.reward.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentRewardBinding
import org.journey.android.reward.dto.CompleteCourseData
import org.journey.android.reward.dto.RewardData

class RewardFragment : BaseFragment<FragmentRewardBinding>() {
    private lateinit var rewardAdapter: RewardAdapter
    private lateinit var completeCourseAdapter: CompleteCourseAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRewardBinding {
      return FragmentRewardBinding.inflate(inflater, container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showReward()
        showCompleteCourse()
        goBackMain()
    }
    private fun showReward(){
        rewardAdapter = RewardAdapter()
        binding.recyclerviewReward.adapter = rewardAdapter
        rewardAdapter.rewardList.addAll(
            listOf<RewardData>(
                RewardData(
                    percent = "09",
                    percentUnit = "%",
                    rewardContent = requireContext().getString(R.string.reward_challenge_percent)
                ),
                RewardData(
                    percent= "09",
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
                    courseComplete = "2021.07.31"
                ),
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "뽀득뽀득 세균 퇴치",
                    courseComplete = "2021.07.31"
                ),
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "뽀득뽀득 세균 퇴치",
                    courseComplete = "2021.07.31"
                ),
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "뽀득뽀득 세균 퇴치",
                    courseComplete = "2021.07.31"
                ),
                CompleteCourseData(
                    courseDate = "7일 코스",
                    courseName = "뽀득뽀득 세균 퇴치",
                    courseComplete = "2021.07.31"
                )
            )
        )
        completeCourseAdapter.notifyDataSetChanged()
    }
    private fun goBackMain(){
        binding.buttonPressedBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}