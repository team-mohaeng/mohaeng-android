package org.journey.android.signup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import org.journey.android.R

class SignupViewPagerFragment : Fragment() {

    lateinit var viewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_view_pager, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signupFragments : ArrayList<Fragment> = arrayListOf(
            SignupFirstFragment(),
            SignupSecondFragment(),
            SignupThirdFragment()
        )
        viewPager = view.findViewById(R.id.viewpager_signup)
        val signupAdapter = SignupViewPagerAdapter(signupFragments, this)
        val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.dotindicator_signup)
        viewPager.adapter = signupAdapter
        wormDotsIndicator.setViewPager2(viewPager)
        viewPager.isUserInputEnabled = false
    }


}