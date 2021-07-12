package org.journey.android.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentMainBinding

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
}