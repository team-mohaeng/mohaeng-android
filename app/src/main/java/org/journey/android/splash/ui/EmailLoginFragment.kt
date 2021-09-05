package org.journey.android.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
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
    }
    private fun setClickListener(){
        with(binding){
            buttonReturn.setOnClickListener { findNavController().popBackStack() }
        }
    }
}