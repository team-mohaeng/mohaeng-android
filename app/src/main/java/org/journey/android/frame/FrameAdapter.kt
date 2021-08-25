package org.journey.android.frame

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.journey.android.challenge.ChallengeFragment
import org.journey.android.community.view.CommunityFragment
import org.journey.android.main.view.MainFragment

class FrameAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> ChallengeFragment()
            2 -> CommunityFragment()
            else -> throw RuntimeException("Error")
        }
        notifyDataSetChanged()
    }


}