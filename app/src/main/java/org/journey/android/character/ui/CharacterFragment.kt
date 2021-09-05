package org.journey.android.character.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentCharacterBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class CharacterFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCharacterBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    private fun setClickListener(){
        with(binding){
            buttonReturnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}