package org.journey.android.nickname

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.databinding.FragmentSetNickNameBinding
import org.journey.android.frame.MainActivity
import org.journey.android.util.AutoClearedValue

class SetNickNameFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentSetNickNameBinding>()
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
    }
    private fun popBackStack(){
        binding.buttonNicknameReturn.setOnClickListener { findNavController().popBackStack() }
    }
    private fun initMain() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }
}