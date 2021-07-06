package org.journey.android.reward

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentRewardBinding

class RewardFragment : BaseFragment<FragmentRewardBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRewardBinding {
      return FragmentRewardBinding.inflate(inflater, container,false)
    }


}