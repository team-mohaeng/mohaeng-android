package org.journey.android.onboarding.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingFifthBinding
import org.journey.android.util.AutoClearedValue
import kotlin.coroutines.CoroutineContext

class OnboardingFifthFragment : Fragment(), CoroutineScope {
    private var binding by AutoClearedValue<FragmentOnboardingFifthBinding>()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            delay(2500)
            withContext(Dispatchers.Main){
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_onboardingFifthFragment_to_onboardingSixthFragment)
            }
        }
    }


}