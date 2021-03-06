package org.journey.android.presentation.main.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentMainBinding
import org.journey.android.presentation.main.home.view.dialog.DialogIndexExplanationFragment
import org.journey.android.presentation.main.home.viewmodel.MainViewModel
import org.journey.android.presentation.util.AutoClearedValue

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentMainBinding>()
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.initMohaengMain()
        animateLottie()
        setNavController()
        showIndexDialog()
    }
    private fun showIndexDialog(){
        with(binding){
            progressbarMain.setOnClickListener {
                val dialog = DialogIndexExplanationFragment()
                dialog.show(childFragmentManager,tag)
            }
        }
    }
    private fun setNavController(){
        with(binding){
            buttonMainChat.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_chatFragment) }
            buttonMainFirst.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_badgeFragment) }
            buttonMainMypage.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_myPageFragment) }
            imageviewBlank.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_challengeFragment) }
            buttonMainReward.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_characterFragment) }
        }
    }
    private fun animateLottie(){
        viewModel.getLottie.observe(viewLifecycleOwner){
            binding.imageviewMohaengCharacter.run { setAnimationFromUrl(it.characterLottie)
                playAnimation()
            }
        }
    }
}

