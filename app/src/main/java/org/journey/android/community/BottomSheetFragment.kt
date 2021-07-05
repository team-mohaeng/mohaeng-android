package org.journey.android.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BaseFragment<FragmentBottomSheetBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBottomSheetBinding {
        return FragmentBottomSheetBinding.inflate(inflater, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}