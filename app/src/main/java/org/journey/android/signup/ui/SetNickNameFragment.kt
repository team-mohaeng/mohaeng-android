package org.journey.android.signup.ui

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
import org.journey.android.EntryActivity
import org.journey.android.preference.UserPreferenceManager
import org.journey.android.signup.viewmodel.NickNameViewModel
import org.journey.android.util.AutoClearedValue
import javax.inject.Inject

@AndroidEntryPoint
class SetNickNameFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentSetNickNameBinding>()
    private val viewModel : NickNameViewModel by viewModels()
    @Inject lateinit var userPreferenceManager: UserPreferenceManager

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
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
            if(userPreferenceManager.fetchUserSnsType().isNullOrEmpty()){
                viewModel.signUpEmail()
                viewModel.saveEmailSignUpInformation()
                userPreferenceManager.saveUserSnsType("")
                viewModel.emailSignUpSuccess.observe(viewLifecycleOwner) { successed ->
                    if(successed) {
                        val intent = Intent(context, EntryActivity::class.java)
                        startActivity(intent)
                    }
                }
            } else{
                viewModel.setNickName()
            }
        }
        userPreferenceManager.saveUserEmail(viewModel.getEmail())
        userPreferenceManager.saveUserPassword(viewModel.getCheckPassword())
        userPreferenceManager.saveUserSnsType("")
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