package org.journey.android.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentServiceAgreeBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class ServiceAgreeFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentServiceAgreeBinding>()
    private val viewModel : SignupViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServiceAgreeBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        popBackStack()
        setNickName()
        activateConfirmButton()
    }
    private fun popBackStack(){
        binding.buttonAgreementReturn.setOnClickListener { findNavController().popBackStack() }
    }
    private fun setNickName() {
        binding.buttonAgreeService.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.action_serviceAgreeFragment_to_setNickNameFragment) }
    }
    private fun activateConfirmButton(){
        viewModel.serviceAgreementList.forEach { service ->
            service.observe(viewLifecycleOwner){
                viewModel.checkEveryServiceAgreement()
                binding.buttonAgreeService.isActivated = (viewModel.wholePolicyAllowed.value == true)
            }
        }
    }
}