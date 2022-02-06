package org.journey.android.presentation.entry.findpw.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentFindPasswordThirdBinding
import org.journey.android.presentation.entry.findpw.viewmodel.FindPassWordViewModel
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class FindPassWordThirdFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentFindPasswordThirdBinding>()
    private val viewModel by viewModels<FindPassWordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFindPasswordThirdBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loginWithNewPassword()
    }

    private fun loginWithNewPassword(){
        binding.buttonSignUpComplete.setOnClickListener {
            viewModel.changePassword()
            findNavController().navigate(R.id.action_findPassWordThirdFragment_to_emailLoginFragment)
        }
    }

}