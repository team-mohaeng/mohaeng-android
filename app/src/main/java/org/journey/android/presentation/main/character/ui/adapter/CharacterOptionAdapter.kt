package org.journey.android.presentation.main.character.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.domain.entity.MohaengCharacterOptionEntity
import org.journey.android.databinding.ItemSelectOptionBinding
import kotlin.properties.Delegates

class CharacterOptionAdapter(val listener : CharacterOptionListener) :
    RecyclerView.Adapter<CharacterOptionAdapter.CharacterOptionViewHolder>() {
    var optionList = mutableListOf<MohaengCharacterOptionEntity>()
    interface CharacterOptionListener{
        fun selectOption(option : Int)
    }
    var optionPosition by Delegates.observable(0) { _, oldPosition, newPosition ->
        if (newPosition in optionList.indices) {
            notifyItemChanged(oldPosition)
            notifyItemChanged(newPosition)
        }
    }
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
        holder.binding.imagebuttonOptionCharacter.setOnClickListener {
            optionPosition = position
            option.optionType?.let { it -> listener.selectOption(it) }
        }
        holder.binding.checkedEffect.visibility = if(position == optionPosition){
            View.VISIBLE
        }else{
            View.INVISIBLE
        }
    }
    override fun getItemCount() = optionList.size

}