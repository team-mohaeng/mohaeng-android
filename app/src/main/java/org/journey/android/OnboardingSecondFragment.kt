package org.journey.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.journey.android.databinding.FragmentOnboardingSecondBinding
import org.journey.android.util.AutoClearedValue

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
            adapter = OnboardingCourseAdapter()
            viewModel.selectCourse.observe(viewLifecycleOwner){
                (adapter as OnboardingCourseAdapter).selectCourse = it.toMutableList()
            }

        }
    }

}