package org.journey.android.course.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.course.viewmodel.CourseViewModel
import org.journey.android.course.ui.adapter.CourseCatalogAdapter
import org.journey.android.databinding.FragmentCourseCatalogBinding
import org.journey.android.util.AutoClearedValue

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
        fetchCourseCatalog()
        setClickListener()
    }

    private fun fetchCourseCatalog(){
        binding.recyclerviewCourseCatalog.apply {
            this.adapter = CourseCatalogAdapter()
            viewModel.courseCatalogList.observe(viewLifecycleOwner){
                (adapter as CourseCatalogAdapter).courseCatalog = it.toMutableList()
            }
        }
    }

    private fun setClickListener(){
        with(binding){
            buttonBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

}