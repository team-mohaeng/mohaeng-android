package org.journey.android.presentation.main.reward.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentBadgeRewardBinding
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class BadgeRewardFragment  : Fragment(){
    private var binding by AutoClearedValue<FragmentBadgeRewardBinding>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgeRewardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        achieveBadge()
    }
    private fun achieveBadge(){
        binding.buttonOngoing.setOnClickListener {
            findNavController().navigate(R.id.action_badgeRewardFragment_to_challengeFragment)
        }
    }

}