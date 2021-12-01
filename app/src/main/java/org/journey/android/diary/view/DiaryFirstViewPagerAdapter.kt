package org.journey.android.diary.view

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DiaryFirstViewPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when(position) {

            0       ->  FeelFirstFragment()
            1       ->  FeelSecondFragment()
            else    ->  FeelThirdFragment()

        }

    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    // 생성 할 Fragment 의 개수
    override fun getCount() = 3

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}