package org.journey.android.nickname

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
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
            binding.buttonSetNickname.isVisible = true
        }
    }


}