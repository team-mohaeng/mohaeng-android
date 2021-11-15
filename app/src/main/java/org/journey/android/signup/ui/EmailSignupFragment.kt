package org.journey.android.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentEmailSignupBinding
import org.journey.android.signup.viewmodel.NickNameViewModel
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class EmailSignupFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEmailSignupBinding>()
    private val viewModel by viewModels<NickNameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        popBackStack()
        successSignUp()

    }
    private fun popBackStack(){
        with(binding){
            buttonReturnEmailSignup.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun successSignUp(){
        binding.buttonSignUpComplete.setOnClickListener {
            viewModel.saveEmailSignUpInformation()
            Navigation.findNavController(binding.root).navigate(R.id.action_emailSignupFragment_to_serviceAgreeFragment)
        }
    }
}
