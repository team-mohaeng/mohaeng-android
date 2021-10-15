package org.journey.android.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentServiceAgreeBinding
import org.journey.android.util.AutoClearedValue

class ServiceAgreeFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentServiceAgreeBinding>()
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
        popBackStack()
        setNickName()
    }
    private fun popBackStack(){
        binding.buttonAgreementReturn.setOnClickListener { findNavController().popBackStack() }
    }
    private fun setNickName() {
        binding.buttonAgreeService.setOnClickListener { Navigation.findNavController(binding.root).navigate(R.id.action_serviceAgreeFragment_to_setNickNameFragment) }
    }
}