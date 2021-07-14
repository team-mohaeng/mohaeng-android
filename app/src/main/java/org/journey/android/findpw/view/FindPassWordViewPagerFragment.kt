package org.journey.android.findpw.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import org.journey.android.R
import org.journey.android.findpw.view.FindPassWordOneFragment
import org.journey.android.findpw.view.FindPassWordThreeFragment
import org.journey.android.findpw.view.FindPassWordTwoFragment
import org.journey.android.findpw.view.FindPassWordViewPagerAdater

class FindPassWordViewPagerFragment : Fragment(){
    lateinit var viewPager : ViewPager2
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_find_password_viewpager_adapter, null)

        val findPassWordFragments : ArrayList<Fragment> = arrayListOf(
            FindPassWordOneFragment(),
            FindPassWordTwoFragment(),
            FindPassWordThreeFragment()
        )
        viewPager = view.findViewById(R.id.viewpager_find_password)
        val findPassWordAdapter = FindPassWordViewPagerAdater(findPassWordFragments, this)
        val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.dotindicator_find_password)
        viewPager.adapter = findPassWordAdapter
        wormDotsIndicator.setViewPager2(viewPager)
        viewPager.isUserInputEnabled = false
        return view
    }

}

