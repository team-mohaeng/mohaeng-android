package org.journey.android.reward.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentRewardBinding

class RewardFragment : BaseFragment<FragmentRewardBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRewardBinding {
        return FragmentRewardBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBackMain()
    }
    private fun goBackMain() {
        binding.buttonPressedBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}