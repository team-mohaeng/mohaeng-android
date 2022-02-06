package org.journey.android.presentation.frame

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import org.journey.android.R
import org.journey.android.presentation.base.BaseFragment
import org.journey.android.databinding.FragmentFrameBinding

class FrameFragment : BaseFragment<FragmentFrameBinding>() {
    private val viewModel: FrameViewModel by activityViewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFrameBinding {
        return FragmentFrameBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initViewPager()
        initBottomNavigation()
    }

    private fun observeViewModel() {
        viewModel.pageIndex.observe(viewLifecycleOwner) { it ->
            binding.viewpagerMain.currentItem = it
            selectBottomNavigation(it)
        }
    }

    private fun initViewPager() = binding.viewpagerMain.run {
        offscreenPageLimit = 2
        adapter = FrameAdapter(this@FrameFragment)
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.changePageIndex(position)
            }
        })
    }

    private fun selectBottomNavigation(pageIndex: Int) {
        binding.bottomNavigation.selectedItemId = when (pageIndex) {
            0 -> R.id.mainFragment
            1 -> R.id.challengeFragment
            2 -> R.id.communityFragment
            else -> throw RuntimeException("Juyae's Bottom Navigation Errrrror")
        }
    }

    private fun initBottomNavigation() = binding.bottomNavigation.run {
        setOnNavigationItemSelectedListener {
            Log.e("pageIndex", viewModel.pageIndex.value.toString())
            viewModel.changePageIndex(
                when (it.itemId) {
                    R.id.mainFragment -> 0
                    R.id.challengeFragment -> 1
                    R.id.communityFragment -> 2
                    else -> throw RuntimeException("Bottom Navigation Error")
                }
            )
            true
        }
    }

}