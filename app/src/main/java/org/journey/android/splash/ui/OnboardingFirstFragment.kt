package org.journey.android.splash.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentOnboardingFirstBinding

class OnboardingFirstFragment : BaseFragment<FragmentOnboardingFirstBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingFirstBinding {
        return FragmentOnboardingFirstBinding.inflate(inflater,container,false)
    }
}