package org.journey.android.presentation.main.mypage.ui.fragment

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
import org.journey.android.presentation.main.mypage.viewmodel.MyPageViewModel
import org.journey.android.presentation.util.AutoClearedValue

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
        navigateToSettingView()
        showCompleteChallengeRecord()
    }
    private fun popBackStack(){
        with(binding){ buttonReturnBack.setOnClickListener { findNavController().popBackStack() } }
    }
    private fun getMyPageResource(){
        with(viewModel){
            myPageResource.observe(viewLifecycleOwner){
                viewModel.getMyPageResource()
            }
        }
    }
    private fun editNickName(){
        binding.buttonEditNickName.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_editNickNameFragment)
        }
    }
    private fun navigateToSettingView(){
        binding.buttonSetting.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_settingFragment)
        }
    }

    private fun showCompleteChallengeRecord(){
        binding.buttonMyPage.setOnClickListener { findNavController().navigate(R.id.action_myPageFragment_to_completeCourseFragment) }
    }


}
