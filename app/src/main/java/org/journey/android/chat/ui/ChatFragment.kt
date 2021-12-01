package org.journey.android.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.chat.viewmodel.ChatViewModel
import org.journey.android.databinding.FragmentChatBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class ChatFragment: Fragment() {
    private var binding by AutoClearedValue<FragmentChatBinding>()
    private val viewModel by viewModels<ChatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setClickListener()
        getFcmMessage()
        viewModel.getPushAlarmChatMessage()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun setClickListener(){
        with(binding){
            buttonPopBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
    private fun getFcmMessage(){
        viewModel.pushAlarmSuccess.observe(viewLifecycleOwner){
            viewModel.pushAlarmSuccess
        }
    }
}