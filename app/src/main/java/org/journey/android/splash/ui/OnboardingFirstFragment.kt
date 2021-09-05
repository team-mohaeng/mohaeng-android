package org.journey.android.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentOnboardingFirstBinding

@AndroidEntryPoint
class OnboardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingFirstBinding {
        return FragmentOnboardingFirstBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    private fun setClickListener(){
        with(binding){
            buttonLoginEmail.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.action_onboardingFirstFragment_to_emailLoginFragment) }
        }
    }
}