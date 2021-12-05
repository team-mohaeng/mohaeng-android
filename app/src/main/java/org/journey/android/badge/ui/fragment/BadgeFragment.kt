package org.journey.android.badge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.badge.data.entity.BadgeEntity
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
        popBackStack()
        viewModel.loadObtainedBadge(id)

    }

    private fun loadBadgeList() {
        binding.recyclerviewObtainedBadge.run {
            this.adapter = BadgeAdapter(object : BadgeAdapter.BadgeListener{
                override fun selectBadge(badge: BadgeEntity) {
                    val bottomSheetFragment = BadgeBottomSheetFragment(badge)
                    bottomSheetFragment.show(childFragmentManager, "show Badge Bottom Sheet")
                    badge.badgeId?.let { viewModel.loadObtainedBadge(it) }
                }

            })
            viewModel.badgeList.observe(viewLifecycleOwner) {
                (adapter as BadgeAdapter).badgeList = it.toMutableList()
                (adapter as BadgeAdapter).notifyDataSetChanged()
            }
        }
    }
    private fun popBackStack() {
        with(binding) { buttonBack.setOnClickListener { findNavController().popBackStack() } }
    }
}