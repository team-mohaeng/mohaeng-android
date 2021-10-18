package org.journey.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.ActivityOnboardingBinding
import org.journey.android.entry.frame.EntryActivity

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startLogin()
    }

    private fun startLogin(){
        binding.textviewSkipOnboarding.setOnClickListener {
            val intent = Intent(this, EntryActivity::class.java)
            startActivity(intent)
        }
    }

}