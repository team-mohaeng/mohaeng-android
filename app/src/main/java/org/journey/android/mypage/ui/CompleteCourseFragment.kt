package org.journey.android.mypage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentCompleteCourseBinding
import org.journey.android.mypage.viewmodel.MyPageViewModel
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class CompleteCourseFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCompleteCourseBinding>()
    private val viewModel by viewModels<MyPageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompleteCourseBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popBackStack()
        fetchCompleteCourse()
    }
    private fun popBackStack(){
        with(binding){
            buttonBack.setOnClickListener { findNavController().popBackStack() }
        }
    }
    private fun fetchCompleteCourse(){
        binding.recyclerviewCompleteCourse.run {
            this.adapter = CompleteCourseAdapter()
            viewModel.getCompleteCourse.observe(viewLifecycleOwner) {
                (adapter as CompleteCourseAdapter).submitList(it)
            }
        }
    }
}