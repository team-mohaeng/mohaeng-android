package org.journey.android.onboarding.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingFirstBinding
import org.journey.android.entry.frame.EntryActivity
import org.journey.android.frame.MainActivity
import org.journey.android.onboarding.viewmodel.OnboardingViewModel
import org.journey.android.preference.UserPreferenceManager
import org.journey.android.util.AutoClearedValue
import org.journey.android.util.Extensions.typeWrite
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFirstFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingFirstBinding>()
    private val viewModel by viewModels<OnboardingViewModel>()
    @Inject lateinit var userPreferenceManager: UserPreferenceManager

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
        loadTypingAnimation()
//        Log.e("user token", userPreferenceManager.fetchUserAccessToken())

    }

    private fun checkHasUserToken() {
        if (userPreferenceManager.fetchUserAccessToken() == "") {
            Log.e("user token", "${userPreferenceManager.fetchUserAccessToken()}")
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    private fun loadTypingAnimation(){
        binding.textviewOnboardingAskText.typeWrite(this, "야~ 모행?!",120L)
//        binding.textviewOnboardingAnswerText.typeWrite(this,"안녕! 만나서 방가워.\n내 집착을 견딜 준비가 되어있어?\n\n나와 함께 재미있는 챌린지를 수행하며\n하루 행복에 더 가까워지길 바라!",100L )
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