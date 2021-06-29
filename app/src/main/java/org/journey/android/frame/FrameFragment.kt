package org.journey.android.frame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentFrameBinding

class FrameFragment : BaseFragment<FragmentFrameBinding>() {
    private val viewModel : FrameViewModel by activityViewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFrameBinding {
        return FragmentFrameBinding.inflate(inflater,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}