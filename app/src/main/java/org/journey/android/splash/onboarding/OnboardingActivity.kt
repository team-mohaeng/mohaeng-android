package org.journey.android.splash.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.ActivityOnboardingBinding
import org.journey.android.preference.SharedPreferencesManager
import org.journey.android.util.TedRxKeyboardObserver
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    @Inject
    lateinit var pref: SharedPreferencesManager
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        setKeyboardObserver()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
    }
    private fun setKeyboardObserver(){
        TedRxKeyboardObserver(this)
            .listen()
            .subscribe()
    }

}