package org.journey.android.login.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentLoginBinding
import org.journey.android.frame.MainActivity
import org.journey.android.login.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel by viewModels<LoginViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        viewModel.getFcmDeviceToken()

        setAction()
        launchKakaoLogin()
        checkLoginSuccess()
        checkGoogleGso()
        animateLogo()
        animateFootPrint()
    }

    private fun setAction(){
        with(binding){
            textviewEmailAccount.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_emailLoginFragment) }
            buttonLoginEmail.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_emailSignupFragment) }
        }
    }

    private fun launchKakaoLogin(){
        binding.buttonLoginKakao.setOnClickListener {
            getKakaoLogin()
        }
    }

    private fun getKakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            } else if (token != null) {
                viewModel.saveAccessToken(token.accessToken)
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                viewModel.kakaoLogin()

            }
        }
        UserApiClient.instance.run {
            if (isKakaoTalkLoginAvailable(requireContext())) {
                loginWithKakaoTalk(requireContext(), callback = callback)
            } else {
                loginWithKakaoAccount(requireContext(), callback = callback)
            }
        }
    }

    private val googleLoginLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                val account = task.getResult(ApiException::class.java)!!

                firebaseAuthWithGoogle(account.idToken!!)
                viewModel.saveAccessToken(account.idToken)

                Log.e(ContentValues.TAG, "${account.idToken}")
                viewModel.googleLogin()
            } catch (e: ApiException) {
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth?.signInWithCredential(credential)
    }

    private fun checkGoogleGso() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.firebase_app_key))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)

        binding.buttonLoginGoogle.setOnClickListener {
            signGoogle()
        }
    }

    private fun signGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        googleLoginLauncher.launch(signInIntent)
    }

    private fun checkLoginSuccess() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) { successed ->
            if(successed) {
                startActivity(Intent(requireContext(), MainActivity::class.java ))
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_serviceAgreeFragment)
            }
        }
        viewModel.isLoginSuccessed.observe(viewLifecycleOwner) { loginStatus ->
            when (loginStatus) {
//                LOGIN_SUCCESS -> {
//                    findNavController().navigate(R.id.action_loginFragment_to_serviceAgreeFragment)
//                }
//                LOGIN_FAIL -> {
//                    Log.e(TAG, "로그인 실패")
//                }
//                else -> {
//                }
            }
        }
    }

    private fun animateLogo(){
        val fadeInText = binding.textviewLoginLogo
        val fadeInSub = binding.textviewLoginMent
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInText.startAnimation(fadeIn)
        fadeInSub.startAnimation(fadeIn)
    }

    private fun animateFootPrint() {
        val fadeInFootPrint = binding.imageviewLoginFoot
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        fadeInFootPrint.startAnimation(fadeIn)
    }
}