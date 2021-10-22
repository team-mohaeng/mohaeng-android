package org.journey.android.entry.frame

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.ActivityEntryBinding
import org.journey.android.preference.SharedPreferencesManager
import org.journey.android.util.TedRxKeyboardObserver
import java.security.MessageDigest
import javax.inject.Inject

@AndroidEntryPoint
class EntryActivity : AppCompatActivity() {
    @Inject
    lateinit var pref: SharedPreferencesManager
    private lateinit var binding: ActivityEntryBinding
    private lateinit var navController: NavController
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initNavController()
        setKeyboardObserver()
        getAppKeyHash()
        startGoogleLogin()
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
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString())
        }
    }

    private fun startGoogleLogin(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.firebase_app_key))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
    }

}