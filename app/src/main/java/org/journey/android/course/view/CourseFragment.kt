package org.journey.android.course.view

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.course.CourseAdapter
import org.journey.android.course.CourseViewModel
import org.journey.android.databinding.FragmentCourseBinding
import org.journey.android.util.AutoClearedValue
import org.journey.android.util.RecyclerViewItemDecoration

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
//        loadOddDateCourse()
    }
    private fun loadCourseRoute(){
        binding.recyclerviewCourse.apply {
            adapter = CourseAdapter()
            val lastCourseItem = (adapter as CourseAdapter).courseSituation.lastIndex
            addItemDecoration(RecyclerViewItemDecoration(-42,0))
            viewModel.courseRoute.observe(viewLifecycleOwner){
                (adapter as CourseAdapter).courseSituation = it
                Log.e("data", "$it")
            }
        }
    }
//
//    private fun loadOddDateCourse(){
//        binding.recyclerviewCourseStamp.apply {
//            this.adapter = CourseDateAdapter()
//            addItemDecoration(RecyclerViewItemDecoration(240,0))
//            viewModel.courseDateList.observe(viewLifecycleOwner){
//                (adapter as CourseDateAdapter).date = it.toMutableList()
//            }
//        }
//    }

}