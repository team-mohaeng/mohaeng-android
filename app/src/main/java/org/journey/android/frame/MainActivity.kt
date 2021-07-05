package org.journey.android.frame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.journey.android.R
import org.journey.android.community.DiaryFirstFragment
import org.journey.android.community.DiaryViewPagerFragment
import org.journey.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()

        //////////////////////////////////////////////// 커밋전에 지워야 될 것
       supportFragmentManager.beginTransaction()
            .replace(R.id.testview, DiaryViewPagerFragment())
            .commit()
        /////////////////////////////////////////////////
    }

    private fun initNavController(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}