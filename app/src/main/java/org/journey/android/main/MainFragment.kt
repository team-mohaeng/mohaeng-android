package org.journey.android.main

import android.graphics.drawable.AnimatedImageDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.data.JourneyRepository
import org.journey.android.databinding.FragmentMainBinding
import org.journey.android.main.dto.ResponseMainModelItem

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToReward()
        binding.textviewConcentrationPercent.setOnClickListener {
            binding.progressbarConcentration.incrementProgressBy(25)
        }
    }

    private fun goToReward() {
        binding.buttonMainFirst.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_rewardFragment)
        }
    }

    private fun setBackground() {
        when (JourneyRepository.progressPercent) {
            // in PROGRESSBAR_FIRST_RANGE -> R.drawable.
        }

    }

    private fun setMainViewImage(backgroundImageDrawable: Int) {
        //binding.imageviewJourney.setImageDrawable(getbackgroundImageDrawable)
    }

    companion object {
        private val PROGRESSBAR_FIRST_RANGE = 1..25
        private val PROGRESSBAR_SECOND_RANGE = 26..50
        private val PROGRESSBAR_THIRD_RANGE = 51..75
        private val PROGRESSBAR_FOURTH_RANGE = 76..100
    }

}