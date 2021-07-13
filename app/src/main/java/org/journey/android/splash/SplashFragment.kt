package org.journey.android.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSplashJourney.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}