package org.journey.android.splash.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
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
}