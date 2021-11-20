package org.journey.android.character.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.character.data.entity.CharacterSkinEntity
import org.journey.android.databinding.ItemCharacterSkinBinding

class CharacterSkinAdapter:
    RecyclerView.Adapter<CharacterSkinAdapter.CharacterSkinViewHolder>() {
    var skinList = mutableListOf<CharacterSkinEntity>()

    class CharacterSkinViewHolder(val binding: ItemCharacterSkinBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSkinViewHolder {
        val binding =
            ItemCharacterSkinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterSkinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterSkinViewHolder, position: Int) {
        val skin = skinList[position]
        holder.binding.setVariable(BR.data, skin)
    }
    override fun getItemCount() = skinList.size
}
