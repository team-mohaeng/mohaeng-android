package org.journey.android.presentation.main.reward.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentCompleteRewardBinding
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class CompleteRewardFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCompleteRewardBinding>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompleteRewardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueChallenge()
    }
    private fun continueChallenge(){
        binding.buttonOngoing.setOnClickListener {
            findNavController().navigate(R.id.action_completeRewardFragment_to_badgeRewardFragment)
        }
    }
}