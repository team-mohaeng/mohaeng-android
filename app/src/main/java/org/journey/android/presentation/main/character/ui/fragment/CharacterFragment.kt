package org.journey.android.presentation.main.character.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.domain.entity.CharacterSkinEntity
import org.journey.android.presentation.main.character.viewmodel.CharacterViewModel
import org.journey.android.presentation.main.character.ui.adapter.CharacterOptionAdapter
import org.journey.android.presentation.main.character.ui.adapter.CharacterSelectAdapter
import org.journey.android.presentation.main.character.ui.adapter.CharacterSkinAdapter
import org.journey.android.databinding.FragmentCharacterBinding
import org.journey.android.presentation.util.AutoClearedValue

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
        observeCharacterOption()
        changeCharacter()
        fetchCharacterSkin()
        showCharacterSkinOption()
        viewModel.loadUserCurrentSkin()
    }
    private fun observeCharacterOption() {
        viewModel.characterInfo.observe(viewLifecycleOwner) {
            selectCharacterOption()
            (binding.recyclerviewSkin.adapter as CharacterSkinAdapter).apply {
                skinList = it.skinList.map { CharacterSkinEntity(it.id, it.image) }.toMutableList()
                notifyDataSetChanged()
            }

        }
    }
    private fun popBackStack() {
        with(binding) { buttonReturnBack.setOnClickListener { findNavController().popBackStack() } }
    }
    private fun selectCharacter() {
        binding.recyclerviewSelectCharacter.apply {
            this.adapter = CharacterSelectAdapter(object : CharacterSelectAdapter.CharacterSelectListener{
                override fun selectCharacter(type: Int) {
                    viewModel.changeSelectedType(type)
                }
            })
            viewModel.characterList.observe(viewLifecycleOwner) {
                (adapter as CharacterSelectAdapter).characterList = it.toMutableList()
            }
        }
    }
    private fun showCharacterSkinOption(){
        binding.buttonCharacterSkin.setOnClickListener {
            binding.recyclerviewSkin.isVisible = true
        }
    }

    private fun fetchCharacterSkin() {
        binding.recyclerviewSkin.run {
            this.adapter = CharacterSkinAdapter(object : CharacterSkinAdapter.CharacterSkinListener{
                override fun selectSkin(skin: Int) {
                    viewModel.changeSelectedSkinType(skin)
                }
            })
        }
    }

    private fun selectCharacterOption(){
        //Default checked index 1 character, skin, option
        viewModel.changeSelectedType(1)
        binding.recyclerviewSelectStyle.apply {
            isNestedScrollingEnabled = false
            this.adapter = CharacterOptionAdapter(object : CharacterOptionAdapter.CharacterOptionListener{
                override fun selectOption(option: Int) {
                    viewModel.changeSelectedOptionType(option)
                }

            })
            viewModel.selectedType.observe(viewLifecycleOwner){ type ->
                val character = viewModel.characterInfo.value?.characterList?.find { it.type == type }
                Log.e("character", "${character}")
                (adapter as CharacterOptionAdapter).optionList = character?.characters?.toMutableList() ?: mutableListOf()
                (adapter as CharacterOptionAdapter).notifyDataSetChanged()
            }
        }
    }
    private fun changeCharacter(){
        binding.buttonCompleteStyle.setOnClickListener {
            viewModel.changeUserCharacter()
            Toast.makeText(context, "스타일이 저장되었습니다.",Toast.LENGTH_SHORT).show()
        }
    }
}