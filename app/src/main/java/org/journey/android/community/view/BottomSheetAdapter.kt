package org.journey.android.community.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.community.dto.BottomSheetData
import org.journey.android.community.dto.BottomSheetDiffCallback
import org.journey.android.databinding.ItemCommunityRecordBinding

class BottomSheetAdapter : ListAdapter<BottomSheetData, BottomSheetAdapter.BottomSheetViewHolder>(
    BottomSheetDiffCallback
) {
    val bottomList = mutableListOf<BottomSheetData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetViewHolder {
        val binding = ItemCommunityRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return BottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.bind(bottomList[position])
        holder.itemView.isSelected = true
    }

    override fun getItemCount(): Int= bottomList.size

    class BottomSheetViewHolder(val binding : ItemCommunityRecordBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(bottomSheetData: BottomSheetData){
                    binding.textviewTagFirst.text = bottomSheetData.first_tag
                    binding.textviewTagSecond.text = bottomSheetData.second_tag
                    binding.textviewRecordContent.text = bottomSheetData.diary
                    binding.textviewUserId.text = bottomSheetData.user_id
                    binding.textviewCountPrefer.text = bottomSheetData.user_prefer
                }
            }

}