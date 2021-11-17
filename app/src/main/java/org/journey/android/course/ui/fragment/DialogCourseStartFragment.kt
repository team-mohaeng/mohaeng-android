package org.journey.android.course.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.course.viewmodel.CourseViewModel
import org.journey.android.databinding.DialogCourseStartBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class DialogCourseStartFragment(private val listener: StartCourseListener)  : DialogFragment() {
    private var binding by AutoClearedValue<DialogCourseStartBinding>()
    private val viewModel by viewModels<CourseViewModel>()

    interface StartCourseListener {
        fun startCourse()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCourseStartBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.selector_index_dialog)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeDialog()
        startCourse()
    }

    private fun startCourse() {
        binding.buttonChange.setOnClickListener {
            listener.startCourse()
            viewModel.putCourseState()
        }
    }

    private fun closeDialog(){
        with(binding){ buttonCancel.setOnClickListener { dismiss() } }
    }

}