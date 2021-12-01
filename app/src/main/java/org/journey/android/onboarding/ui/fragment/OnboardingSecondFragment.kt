package org.journey.android.onboarding.ui.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingSecondBinding
import org.journey.android.onboarding.ui.adapter.OnboardingCourseAdapter
import org.journey.android.onboarding.viewmodel.OnboardingViewModel
import org.journey.android.util.AutoClearedValue
import org.journey.android.util.Extensions.typeWrite
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OnboardingSecondFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingSecondBinding>()
    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingSecondBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        fetchCourseRecyclerview()
        animateTyping()

    }

    private fun fetchCourseRecyclerview(){
        binding.recyclerviewChooseCourse.apply{
            isNestedScrollingEnabled = false
            adapter = OnboardingCourseAdapter(object : OnboardingCourseAdapter.CourseSelectListener{
                override fun onClick() {
                    findNavController().navigate(R.id.action_onboardingSecondFragment_to_onboardingThirdFragment)
                }
            } )
            viewModel.selectCourse.observe(viewLifecycleOwner){
                (adapter as OnboardingCourseAdapter).selectCourse = it.toMutableList()
            }

        }
    }
    private fun animateTyping() {
        val textview = binding.textviewOnboardingTitle
        val text = requireContext().getString(R.string.onboarding_explan_challenge)
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
                binding.textviewChooseCourse.isVisible = true
            })
    }

}