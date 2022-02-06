package org.journey.android.presentation.onboarding.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.R
import org.journey.android.presentation.main.challenge.ui.dialog.ChallengeCertifyDialog
import org.journey.android.databinding.FragmentOnboardingThirdBinding
import org.journey.android.presentation.util.AutoClearedValue
import java.util.concurrent.TimeUnit

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
        animateTyping()
    }

    private fun validateChallengeStamp() {
        binding.buttonStamp.setOnClickListener {
            val dialog = ChallengeCertifyDialog(object : ChallengeCertifyDialog.CertifyChallengeListener{
                override fun certifyCourse() {
                    findNavController().navigate(R.id.action_onboardingThirdFragment_to_onboardingFourthFragment)
                }
            })
            dialog.show(childFragmentManager, tag)
        }
    }

    private fun animateTyping() {
        val textview = binding.textviewOnboardingTitle
        val text = requireContext().getString(R.string.onboarding_greeting_met)
        var textCount = 0
        var textStack = ""
        Observable.interval(60, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .take(text.length.toLong())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                textStack += text[textCount].toString()
                textview.setText(textStack)
                textCount++
            },{
                it.printStackTrace()
            }, {
                binding.textviewOnboardingSubtitle.isVisible = true
            })
    }

}