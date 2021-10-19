package org.journey.android.onboarding.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingFirstBinding
import org.journey.android.entry.frame.EntryActivity
import org.journey.android.onboarding.OnboardingViewModel
import org.journey.android.util.AutoClearedValue

class OnboardingFirstFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingFirstBinding>()
    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startLogin()
        startOnboarding()
    }

    private fun startLogin(){
        binding.textviewSkipOnboarding.setOnClickListener {
            val intent = Intent(context, EntryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startOnboarding(){
        binding.buttonOnboardingStart.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFirstFragment_to_onboardingSecondFragment)
        }
    }

}