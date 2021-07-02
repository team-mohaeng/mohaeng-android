package org.journey.android.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentCommunityBinding

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(inflater, container, false)
    }


}