package org.journey.android.presentation.main.mypage.ui.fragment

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
import org.journey.android.R
import org.journey.android.databinding.FragmentEditNickNameBinding
import org.journey.android.presentation.preference.UserPreferenceManager
import org.journey.android.presentation.entry.signup.viewmodel.NickNameViewModel
import org.journey.android.presentation.util.AutoClearedValue
import javax.inject.Inject

@AndroidEntryPoint
class EditNickNameFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEditNickNameBinding>()
    private val viewModel : NickNameViewModel by viewModels()
    @Inject lateinit var userPreferenceManager: UserPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNickNameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.edittextEditNickname.hint = viewModel.getNickName()
        setButtonVisible()
        popBackStack()
        changeNickName()
    }
    private fun changeNickName() {
        binding.buttonSetNickname.setOnClickListener {
            viewModel.changeNickName()
            findNavController().navigate(R.id.action_editNickNameFragment_to_mainFragment)
        }
    }
    private fun setButtonVisible(){
        binding.edittextEditNickname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.buttonSetNickname.isVisible = false
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.buttonSetNickname.isVisible = true
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
    private fun popBackStack(){
        binding.buttonNicknameReturn.setOnClickListener { findNavController().popBackStack() }
    }

}