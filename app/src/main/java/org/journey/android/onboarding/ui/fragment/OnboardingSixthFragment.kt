package org.journey.android.onboarding.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingSixthBinding
import org.journey.android.EntryActivity
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class OnboardingSixthFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingSixthBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingSixthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMohaeng()
        loadFadingEffect()
    }

    private fun startMohaeng(){
        binding.buttonStartMohaeng.setOnClickListener {
            //Todo 로그인된 유저는 바로 메인홈, 계정이 없을경우 로그인
            val intent = Intent(context, EntryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadFadingEffect() {
        val fadeInMent = binding.textviewOnboardingLastMent
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInMent.startAnimation(fadeIn)
    }
}