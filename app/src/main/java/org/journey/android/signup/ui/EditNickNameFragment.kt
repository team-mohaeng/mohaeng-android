package org.journey.android.signup.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentEditNickNameBinding
import org.journey.android.signup.viewmodel.NickNameViewModel
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class EditNickNameFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentEditNickNameBinding>()
    private val viewModel : NickNameViewModel by viewModels()

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
        changeNickName()
        setButtonVisible()
    }
    private fun changeNickName() {
        viewModel.changeNickName.observe(viewLifecycleOwner){
            viewModel.changeNickName()
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
}