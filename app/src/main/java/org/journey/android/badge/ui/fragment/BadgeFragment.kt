package org.journey.android.badge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.badge.ui.adapter.BadgeAdapter
import org.journey.android.badge.viewmodel.BadgeViewModel
import org.journey.android.databinding.FragmentBadgeBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class BadgeFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentBadgeBinding>()
    private val viewModel by viewModels<BadgeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBadgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadBadgeList()
        clickAssetListener()
        viewModel.loadObtainedBadge()
    }

    private fun loadBadgeList() {
        binding.recyclerviewObtainedBadge.run {
            this.adapter = BadgeAdapter()
            viewModel.badgeList.observe(viewLifecycleOwner) {
                (adapter as BadgeAdapter).badgeList = it.toMutableList()
                (adapter as BadgeAdapter).notifyDataSetChanged()
            }
        }
    }


    private fun clickAssetListener() {
        with(binding) {
            buttonBack.setOnClickListener { findNavController().popBackStack() }
        }
    }
}