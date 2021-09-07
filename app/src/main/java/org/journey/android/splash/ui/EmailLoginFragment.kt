package org.journey.android.splash.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentEmailLoginBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class EmailLoginFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEmailLoginBinding>()
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
        setClickListener()
        setTextWatcher()
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
                     buttonLoginComplete.isSelected =edittextInputEmail.text.toString().isNotEmpty()
                }
                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }


}