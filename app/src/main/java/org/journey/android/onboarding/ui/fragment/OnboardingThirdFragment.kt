package org.journey.android.onboarding.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.challenge.ui.dialog.ChallengeCertifyDialog
import org.journey.android.databinding.FragmentOnboardingThirdBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class OnboardingThirdFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingThirdBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateChallengeStamp()
    }
    private fun validateChallengeStamp(){
        binding.buttonStamp.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingThirdFragment_to_onboardingFourthFragment)
//                val dialog = ChallengeCertifyDialog()
//                dialog.show(childFragmentManager,tag)
        }
    }


}