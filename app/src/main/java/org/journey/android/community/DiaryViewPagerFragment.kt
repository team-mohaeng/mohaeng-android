package org.journey.android.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import org.journey.android.R

class DiaryViewPagerFragment : Fragment(){
    lateinit var viewPager : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_diary_viewpager_adapter, null)

        val diaryFragments : ArrayList<Fragment> = arrayListOf(
            DiaryFirstFragment(),
            DiarySecondFragment()
        )
        viewPager = view.findViewById(R.id.viewpager_diary)
        val diaryAdapter = DiaryViewPagerAdapter(diaryFragments, this)
        val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.dotindicator_diary)
        viewPager.adapter = diaryAdapter
        wormDotsIndicator.setViewPager2(viewPager)
        viewPager.isUserInputEnabled = false


        return view
    }
}