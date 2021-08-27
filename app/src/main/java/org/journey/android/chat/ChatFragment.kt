package org.journey.android.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.databinding.FragmentChatBinding
import org.journey.android.util.AutoClearedValue

class ChatFragment: Fragment() {
    private var binding by AutoClearedValue<FragmentChatBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
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
}