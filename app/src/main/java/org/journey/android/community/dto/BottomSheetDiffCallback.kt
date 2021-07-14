package org.journey.android.community.dto

import androidx.recyclerview.widget.DiffUtil
import org.journey.android.community.dto.BottomSheetData

object BottomSheetDiffCallback : DiffUtil.ItemCallback<BottomSheetData>() {
    override fun areItemsTheSame(oldItem: BottomSheetData, newItem: BottomSheetData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
    override fun areContentsTheSame(oldItem: BottomSheetData, newItem: BottomSheetData): Boolean {
        return oldItem == newItem
    }

}