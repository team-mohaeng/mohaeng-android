package org.journey.android.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.journey.android.R
import org.journey.android.databinding.ActivityOnboardingBinding
import org.journey.android.presentation.main.MainActivity
import org.journey.android.presentation.preference.UserPreferenceManager
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnboardingBinding
    private lateinit var navController: NavController
    @Inject lateinit var userPreferenceManager: UserPreferenceManager
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var keyHash = Utility.getKeyHash(this)
        Log.d("KEY_HASH", keyHash)
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    private fun handleFlow() {
        val isAlreadyLogin = userPreferenceManager.fetchIsAlreadyLogIn()
        Log.e("isAlready", "$isAlreadyLogin")
        when {
            isAlreadyLogin -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else -> {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

}