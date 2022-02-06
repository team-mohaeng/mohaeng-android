package org.journey.android.presentation.main.course.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.presentation.main.course.viewmodel.CourseViewModel
import org.journey.android.presentation.main.course.ui.adapter.CourseCatalogAdapter
import org.journey.android.databinding.FragmentCourseCatalogBinding
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class CourseCatalogFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCourseCatalogBinding>()
    private val viewModel by viewModels<CourseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentCourseCatalogBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.fetchCatalogList()
        fetchCourseCatalog()
        popBackStack()
    }
    private fun fetchCourseCatalog(){
        binding.recyclerviewCourseCatalog.apply {
            this.adapter = CourseCatalogAdapter(object : CourseCatalogAdapter.OnItemClickListener{
                override fun selectCourse(courseId :Int) {
                    viewModel.changeCourseId(courseId)
                    val dialog = DialogCourseStartFragment(object: DialogCourseStartFragment.StartCourseListener{
                        override fun startCourse() {
                            viewModel.putCourseState()
                            findNavController().previousBackStackEntry?.savedStateHandle?.set("ddd", true)
                            findNavController().popBackStack()
                        }
                    })
                    dialog.show(childFragmentManager , "dialog")
                }
            })
            viewModel.courseCatalogList.observe(viewLifecycleOwner){
                (adapter as CourseCatalogAdapter).courseCatalog = it.toMutableList()
                (adapter as CourseCatalogAdapter).notifyDataSetChanged()
            }
        }
    }
    private fun popBackStack(){
        with(binding){ buttonBack.setOnClickListener { findNavController().popBackStack() } }
    }
}