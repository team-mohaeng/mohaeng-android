package org.journey.android.login.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentLoginBinding
import org.journey.android.login.LoginViewModel
import org.journey.android.login.LoginViewModel.Companion.LOGIN_FAIL
import org.journey.android.login.LoginViewModel.Companion.LOGIN_SUCCESS

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
        setAction()
        launchKakaoLogin()
        checkLoginSuccess()
    }

    private fun setAction(){
        with(binding){
            textviewEmailAccount.setOnClickListener { Navigation.findNavController(binding.root).navigate(
                R.id.action_loginFragment_to_emailLoginFragment
            ) }
            buttonLoginEmail.setOnClickListener { Navigation.findNavController(binding.root).navigate(
                R.id.action_loginFragment_to_emailSignupFragment
            ) }
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
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                findNavController().navigate(R.id.action_loginFragment_to_serviceAgreeFragment)
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

    private fun checkLoginSuccess() {
        viewModel.isLoginSuccessed.observe(viewLifecycleOwner) { loginStatus ->
            when (loginStatus) {
                LOGIN_SUCCESS -> {
                    findNavController().navigate(R.id.action_loginFragment_to_serviceAgreeFragment)
                }
                LOGIN_FAIL -> {
                    Log.e(TAG, "로그인 실패")
                }
                else -> {
                }
            }
        }
    }



}