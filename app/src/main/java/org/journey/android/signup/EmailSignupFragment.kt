package org.journey.android.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentEmailSignupBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class EmailSignupFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEmailSignupBinding>()

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
            Navigation.findNavController(binding.root).navigate(R.id.action_emailSignupFragment_to_serviceAgreeFragment)
        }
    }
}
