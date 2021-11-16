package org.journey.android.onboarding.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingFirstBinding
import org.journey.android.entry.frame.EntryActivity
import org.journey.android.frame.MainActivity
import org.journey.android.onboarding.viewmodel.OnboardingViewModel
import org.journey.android.preference.UserPreferenceManager
import org.journey.android.util.AutoClearedValue
import org.journey.android.util.Extensions.applyVisibilityAnimation
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFirstFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingFirstBinding>()
    private val viewModel by viewModels<OnboardingViewModel>()
    @Inject lateinit var userPreferenceManager: UserPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startLogin()
        startOnboarding()
        showAskingTextBalloon()
//        checkHasUserToken()
    }

    private fun checkHasUserToken() {
        if (userPreferenceManager.fetchUserAccessToken() != "") {
            Log.e("user token", "${userPreferenceManager.fetchUserAccessToken()}")
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    private fun showAskingTextBalloon() {
        val animationSet = binding.imageviewOnboardingQuestion.applyVisibilityAnimation(1000L)
        animationSet.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.imageviewOnboardingQuestion.visibility = View.VISIBLE
                animateTyping()
                showAnswerTextBalloon()
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

    private fun showAnswerTextBalloon() {
        val animationSet = binding.imageviewOnboardingAnswer.applyVisibilityAnimation(1000L)
        animationSet.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.imageviewOnboardingAnswer.visibility = View.VISIBLE
                animateSecondTyping()
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

    private fun animateTyping() {
        val textview = binding.textviewOnboardingAskText
        val text = requireContext().getString(R.string.onboarding_text_one)
        var textCount = 0
        var textStack = ""
        Observable.interval(120, TimeUnit.MILLISECONDS)
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
                animateSecondTyping()
            })
    }

    private fun animateSecondTyping() {
        val textview = binding.textviewOnboardingAnswerText
        val text = requireContext().getString(R.string.onboarding_answer_text)
        var textCount = 0
        var textStack = ""
        Observable.interval(70, TimeUnit.MILLISECONDS)
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

            })
    }

    private fun startLogin(){
        binding.textviewSkipOnboarding.setOnClickListener {
            val intent = Intent(context, EntryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startOnboarding(){
        binding.buttonOnboardingStart.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFirstFragment_to_onboardingSecondFragment)
        }
    }
}

