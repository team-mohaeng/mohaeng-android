package org.journey.android.entry.frame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.ActivityEntryBinding
import org.journey.android.preference.SharedPreferencesManager
import org.journey.android.util.TedRxKeyboardObserver
import javax.inject.Inject

@AndroidEntryPoint
class EntryActivity : AppCompatActivity() {
    @Inject
    lateinit var pref: SharedPreferencesManager
    private lateinit var binding: ActivityEntryBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
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