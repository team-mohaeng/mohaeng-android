package org.journey.android.character.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.R
import org.journey.android.character.data.entity.MohaengCharacterEntity
import org.journey.android.databinding.ItemSelectCharacterBinding
import kotlin.properties.Delegates

class CharacterSelectAdapter (val listener : CharacterSelectListener): RecyclerView.Adapter<CharacterSelectAdapter.CharacterSelectViewHolder>() {
    var characterList = mutableListOf<MohaengCharacterEntity>()
    interface CharacterSelectListener{
        fun selectCharacter(type: Int)
    }

    var selectedPosition by Delegates.observable(0) { _, oldPosition, newPosition ->
        if (newPosition in characterList.indices) {
            notifyItemChanged(oldPosition)
            notifyItemChanged(newPosition)
        }
    }

    class CharacterSelectViewHolder(val binding: ItemSelectCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterSelectViewHolder {
        val binding =
            ItemSelectCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterSelectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterSelectViewHolder, position: Int) {
        val character = characterList[position]
        holder.binding.setVariable(BR.data,character)
        holder.binding.itemSelectCharacter.setOnClickListener {
            selectedPosition = position
            character.type?.let { it -> listener.selectCharacter(it) }
        }
        holder.binding.backgroundTouchEffect.visibility = if (position == selectedPosition) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun getItemCount() = characterList.size

}