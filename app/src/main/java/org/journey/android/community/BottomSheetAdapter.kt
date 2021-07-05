package org.journey.android.community

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemCommunityRecordBinding


class BottomSheetAdapter(val listener: Context) : RecyclerView.Adapter<BottomSheetAdapter.BottomSheetViewHolder>(){
    val diffCallback = object : DiffUtil.ItemCallback<BottomSheetData>(){
        override fun areItemsTheSame(oldItem: BottomSheetData, newItem: BottomSheetData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
        override fun areContentsTheSame(
            oldItem: BottomSheetData,
            newItem: BottomSheetData
        ): Boolean {
            return oldItem == newItem
        }
    }
    var bottomSheetData = mutableListOf<BottomSheetData>()

    interface OnItemClickListener{
        fun itemClickListener(view: View, position: Int)
    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetAdapter.BottomSheetViewHolder {
        val binding = ItemCommunityRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return BottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetAdapter.BottomSheetViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.setVariable(BR.data, item)


    }
    override fun getItemCount(): Int = differ.currentList.size

    inner class BottomSheetViewHolder(val binding: ItemCommunityRecordBinding) : RecyclerView.ViewHolder(binding.root)
}