package org.journey.android.presentation.main.character.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.domain.entity.CharacterSkinEntity
import org.journey.android.databinding.ItemCharacterSkinBinding
import kotlin.properties.Delegates

class CharacterSkinAdapter(val listener : CharacterSkinListener):
    RecyclerView.Adapter<CharacterSkinAdapter.CharacterSkinViewHolder>() {
    var skinList = mutableListOf<CharacterSkinEntity>()
    interface CharacterSkinListener{
        fun selectSkin(skin : Int)
    }

    var skinPosition by Delegates.observable(0) { _, oldPosition, newPosition ->
        if (newPosition in skinList.indices) {
            notifyItemChanged(oldPosition)
            notifyItemChanged(newPosition)
        }
    }
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
        holder.binding.imageviewSkin.setOnClickListener {
            skinPosition = position
            skin.skinType?.let { it -> listener.selectSkin(it) }
        }
        holder.binding.imageviewSkinSelect.visibility = if(position == skinPosition){
            View.VISIBLE
        } else{
            View.INVISIBLE
        }

    }
    override fun getItemCount() = skinList.size
}
