package org.journey.android.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentLoginBinding
import org.journey.android.login.LoginViewModel

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
    }

    private fun setAction(){
        with(binding){
            textviewEmailAccount.setOnClickListener { Navigation.findNavController(binding.root).navigate(
                R.id.action_onboardingFirstFragment_to_emailLoginFragment
            ) }
            buttonLoginEmail.setOnClickListener { Navigation.findNavController(binding.root).navigate(
                R.id.action_onboardingFirstFragment_to_emailSignupFragment
            ) }
        }
    }

}