package org.journey.android.presentation.entry

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.ActivityEntryBinding
import org.journey.android.presentation.util.TedRxKeyboardObserver
import java.security.MessageDigest

@AndroidEntryPoint
class EntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEntryBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        setKeyboardObserver()
        getAppKeyHash()
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
    private fun getAppKeyHash() {
        var keyHash = Utility.getKeyHash(this)
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {
            Log.e("name not found", e.toString())
        }
    }
}