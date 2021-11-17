package org.journey.android.mypage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentMyPageBinding
import org.journey.android.mypage.viewmodel.MyPageViewModel
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentMyPageBinding>()
    private val viewModel : MyPageViewModel by viewModels()
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getMyPageResource()
        popBackStack()
        getMyPageResource()
        editNickName()
    }
    private fun popBackStack(){
        with(binding){ buttonReturnBack.setOnClickListener { findNavController().popBackStack() } }
    }
    private fun getMyPageResource(){
        viewModel.myPageResource.observe(viewLifecycleOwner){
            viewModel.getMyPageResource()
        }
    }
    private fun editNickName(){
        binding.buttonEditNickName.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_editNickNameFragment)
        }
    }

}

