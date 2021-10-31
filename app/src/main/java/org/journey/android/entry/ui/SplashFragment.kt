package org.journey.android.entry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentSplashBinding
import kotlin.coroutines.CoroutineContext

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
        launch {
            delay(2000)
            withContext(Dispatchers.Main){
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_splashFragment_to_onboardingFirstFragment)
            }
        }
    }
}