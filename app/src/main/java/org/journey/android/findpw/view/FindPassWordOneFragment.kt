package org.journey.android.findpw.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordOneBinding
import org.journey.android.findpw.viewmodel.FindPassWordViewModel
import org.journey.android.preference.UserPreferenceManager
import org.journey.android.util.AutoClearedValue
import javax.inject.Inject

@AndroidEntryPoint
class FindPassWordOneFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentFindPasswordOneBinding>()
    private val viewModel by viewModels<FindPassWordViewModel>()
    @Inject lateinit var userPreferenceManager: UserPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindPasswordOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setTextWatcher()
        setClickListener()
        sendEmailVerification()
    }
    private fun setTextWatcher(){
        with(binding){
            edittextInputEmail.addTextChangedListener(object :  TextWatcher {
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
    private fun setClickListener(){
        with(binding){
            buttonReturnBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun sendEmailVerification(){
        binding.buttonSendVerification.setOnClickListener {
            viewModel.sendVericiationCode()

            findNavController().navigate(R.id.action_findPassWordOneFragment_to_findPassWordSecondFragment)
        }
    }

}

