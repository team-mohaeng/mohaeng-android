package org.journey.android.mypage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentMyPageBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentMyPageBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }
    private fun setClickListener(){
        with(binding){
            buttonReturnBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

}