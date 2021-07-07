package org.journey.android.community

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.util.OnSwipeTouchListener

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    val bottomSheetFragment = BottomSheetFragment()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this


    }
}

