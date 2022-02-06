package org.journey.android.presentation.main.course.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.presentation.main.course.viewmodel.CourseViewModel
import org.journey.android.presentation.main.course.ui.adapter.CourseAdapter
import org.journey.android.databinding.FragmentCourseBinding
import org.journey.android.presentation.util.AutoClearedValue
import org.journey.android.presentation.util.RecyclerViewItemDecoration

@AndroidEntryPoint
class CourseFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCourseBinding>()
    private val viewModel by viewModels<CourseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadCourseRoute()
    }
    private fun loadCourseRoute(){
        binding.recyclerviewCourse.apply {
            adapter = CourseAdapter()
            val lastCourseItem = (adapter as CourseAdapter).courseSituation.lastIndex
            addItemDecoration(RecyclerViewItemDecoration(-54,0))
            viewModel.courseRoute.observe(viewLifecycleOwner){
                (adapter as CourseAdapter).courseSituation = it
                Log.e("data", "$it")
            }
        }
    }

}