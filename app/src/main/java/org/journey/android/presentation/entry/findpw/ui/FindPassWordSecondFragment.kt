package org.journey.android.presentation.entry.findpw.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordSecondBinding
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class FindPassWordSecondFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentFindPasswordSecondBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindPasswordSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextWatcher()
        setNewPassword()
        popBackStack()
    }

    private fun setTextWatcher(){
        with(binding){
            edittextInputEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    buttonSendVerification.isActivated =edittextInputEmail.text.toString().isNotEmpty()
                }
                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }
    }

    private fun setNewPassword(){
        binding.buttonSendVerification.setOnClickListener {
            findNavController().navigate(R.id.action_findPassWordSecondFragment_to_findPassWordThirdFragment)
        }
    }

    private fun popBackStack(){
        binding.buttonReturnBack.setOnClickListener { findNavController().popBackStack() }
    }

}