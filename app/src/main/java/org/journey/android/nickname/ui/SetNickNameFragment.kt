package org.journey.android.nickname.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentSetNickNameBinding
import org.journey.android.frame.MainActivity
import org.journey.android.nickname.NickNameViewModel
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class SetNickNameFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentSetNickNameBinding>()
    private val viewModel : NickNameViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetNickNameBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popBackStack()
        initMain()
        checkNickName()
        setButtonVisible()
    }
    private fun popBackStack(){
        binding.buttonNicknameReturn.setOnClickListener { findNavController().popBackStack() }
    }
    private fun initMain() {
        binding.buttonSetNickname.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun checkNickName(){
        viewModel.nickname.observe(viewLifecycleOwner){
            viewModel.checkNickNameAvailable()
        }
    }
    private fun setButtonVisible(){
        binding.edittextSetNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.buttonSetNickname.isVisible = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkNickName()
                binding.buttonSetNickname.isVisible = true
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

    }


}