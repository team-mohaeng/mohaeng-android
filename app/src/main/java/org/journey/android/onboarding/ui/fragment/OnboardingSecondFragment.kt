package org.journey.android.onboarding.ui.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentOnboardingSecondBinding
import org.journey.android.onboarding.ui.adapter.OnboardingCourseAdapter
import org.journey.android.onboarding.viewmodel.OnboardingViewModel
import org.journey.android.util.AutoClearedValue

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
}