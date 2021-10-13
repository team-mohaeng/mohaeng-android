package org.journey.android.character.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.character.data.MohaengCharacterOptionEntity
import org.journey.android.databinding.ItemSelectOptionBinding

class CharacterOptionAdapter :
    RecyclerView.Adapter<CharacterOptionAdapter.CharacterOptionViewHolder>() {
    var optionList = mutableListOf<MohaengCharacterOptionEntity>()
    class CharacterOptionViewHolder(val binding: ItemSelectOptionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterOptionViewHolder {
        val binding =
            ItemSelectOptionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterOptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterOptionViewHolder, position: Int) {
        val option = optionList[position]
        holder.binding.setVariable(BR.data,option)
    }

    override fun getItemCount() = optionList.size

}