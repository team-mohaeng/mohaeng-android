package org.journey.android.character.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.character.data.entity.MohaengCharacterEntity
import org.journey.android.databinding.ItemSelectCharacterBinding

class CharacterSelectAdapter :
    RecyclerView.Adapter<CharacterSelectAdapter.CharacterSelectViewHolder>() {
    var characterList = mutableListOf<MohaengCharacterEntity>()

    class CharacterSelectViewHolder(val binding: ItemSelectCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSelectViewHolder {
        val binding =
            ItemSelectCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterSelectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterSelectViewHolder, position: Int) {
        val character = characterList[position]
        holder.binding.setVariable(BR.data,character)
    }

    override fun getItemCount() = characterList.size

}