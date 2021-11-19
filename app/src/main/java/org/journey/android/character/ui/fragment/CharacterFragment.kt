package org.journey.android.character.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.character.viewmodel.CharacterViewModel
import org.journey.android.character.ui.adapter.CharacterOptionAdapter
import org.journey.android.character.ui.adapter.CharacterSelectAdapter
import org.journey.android.databinding.FragmentCharacterBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class CharacterFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCharacterBinding>()
    private val viewModel by viewModels<CharacterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        popBackStack()
        selectCharacter()
        selectCharacterOption()
        viewModel.loadUserCurrentSkin()

    }
    private fun popBackStack() {
        with(binding) { buttonReturnBack.setOnClickListener { findNavController().popBackStack() } }
    }
    private fun selectCharacter() {
        binding.recyclerviewSelectCharacter.apply {
            this.adapter = CharacterSelectAdapter()
            viewModel.loadCharacterList()
            viewModel.characterList.observe(viewLifecycleOwner) {
                (adapter as CharacterSelectAdapter).characterList = it.toMutableList()
            }
        }
    }
    private fun selectCharacterOption(){
        binding.recyclerviewSelectStyle.apply {
            this.adapter = CharacterOptionAdapter()
            viewModel.optionList.observe(viewLifecycleOwner){
                (adapter as CharacterOptionAdapter).optionList = it.toMutableList()
            }
        }
    }
}