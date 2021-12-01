package org.journey.android.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import org.journey.android.R
import org.journey.android.databinding.FragmentSplashBinding
import org.journey.android.util.AutoClearedValue
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SplashFragment : Fragment(), CoroutineScope{
    private var binding by AutoClearedValue<FragmentSplashBinding>()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchSplash()
        animateLogo()
        animateFadeIn()
        animateFootPrint()
    }

    private fun launchSplash(){
        launch {
            delay(2500)
            withContext(Dispatchers.Main){
               findNavController().navigate(R.id.action_splashFragment_to_onboardingFirstFragment)
            }
        }
    }

    private fun animateLogo(){
        val fadeInImage = binding.textviewSplashMohaeng
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInImage.startAnimation(animation)
    }

    private fun animateFadeIn(){
        val fadeInText = binding.textviewSplashWelcomeMent
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInText.startAnimation(fadeIn)
    }

    private fun animateFootPrint() {
        val fadeInFootPrint = binding.imageviewSplashFootprint
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInFootPrint.startAnimation(fadeIn)
    }
}