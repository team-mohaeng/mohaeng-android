package org.journey.android.presentation.frame

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.journey.android.presentation.main.challenge.ui.fragment.ChallengeFragment
import org.journey.android.presentation.main.community.ui.fragment.CommunityFragment
import org.journey.android.presentation.main.home.view.fragment.MainFragment

class FrameAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> ChallengeFragment()
            2 -> CommunityFragment()
            else -> throw RuntimeException("Fragment Load Error")
        }
        notifyDataSetChanged()
    }

}