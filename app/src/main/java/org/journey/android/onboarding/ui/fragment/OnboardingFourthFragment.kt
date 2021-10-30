package org.journey.android.onboarding.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingFourthBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class OnboardingFourthFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingFourthBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingFourthBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skipOnboardingCourse()
    }
    private fun skipOnboardingCourse(){
        binding.buttonSkipCourse.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFourthFragment_to_onboardingFifthFragment)
        }
    }

}