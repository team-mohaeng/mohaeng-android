package org.journey.android.presentation.entry.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentEmailLoginBinding
import org.journey.android.presentation.main.MainActivity
import org.journey.android.presentation.entry.login.viewmodel.LoginViewModel
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class EmailLoginFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEmailLoginBinding>()
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setClickListener()
        setTextWatcher()
        setEmailLogin()
    }
    private fun setClickListener(){
        with(binding){
            buttonReturn.setOnClickListener { findNavController().popBackStack() }
            textviewForgotPassword.setOnClickListener { Navigation.findNavController(binding.root).navigate(
                R.id.action_emailLoginFragment_to_findPassWordOneFragment
            ) }
        }
    }
    private fun setTextWatcher(){
        with(binding){
            edittextInputEmail.addTextChangedListener(object :  TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                     buttonLoginComplete.isActivated =edittextInputEmail.text.toString().isNotEmpty()
                }
                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }
    private fun setEmailLogin(){
        binding.buttonLoginComplete.setOnClickListener {
            viewModel.signIn()
        }
        viewModel.loginSuccess.observe(viewLifecycleOwner) { successed ->
            if(successed) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }
    }
}