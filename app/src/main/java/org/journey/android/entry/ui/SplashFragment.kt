package org.journey.android.entry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentSplashBinding
import org.journey.android.util.Extensions.applyVisibilityAnimation
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() , CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fadeInImage = binding.textviewSplashMohaeng
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInImage.startAnimation(animation)
        animateFadeIn()
        animateFootPrint()
        launch {
            delay(2500)
            withContext(Dispatchers.Main){
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_splashFragment_to_onboardingFirstFragment)
            }
        }
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