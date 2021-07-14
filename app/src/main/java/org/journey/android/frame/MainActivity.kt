package org.journey.android.frame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.journey.android.R
import org.journey.android.challenge.ChallengeFragment
import org.journey.android.data.JourneyRepository
import org.journey.android.databinding.ActivityMainBinding
import org.journey.android.diary.*
import org.journey.android.diary.view.DiarySecondFragment
import org.journey.android.diary.view.PrivateDetailFragment
import org.journey.android.diary.view.PrivateFragment
import org.journey.android.findpw.FindPassWordOneFragment
import org.journey.android.findpw.FindPassWordThreeFragment
import org.journey.android.findpw.FindPassWordTwoFragment
import org.journey.android.findpw.FindPassWordViewPagerFragment
import org.journey.android.login.view.LoginFragment
import org.journey.android.signup.SignupFirstFragment
import org.journey.android.signup.SignupViewPagerFragment

var userToken = ""

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "FirebasePractice.TAG",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            } else {
                val token = task.result
                val msg = getString(R.string.msg_token_fmt, token)
                userToken = token.toString()
                Log.d("fbPractice.Success", msg)
            }
        })
    }
    private fun initNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}