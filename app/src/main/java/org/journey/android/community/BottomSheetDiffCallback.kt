package org.journey.android.community

import androidx.recyclerview.widget.DiffUtil

object BottomSheetDiffCallback : DiffUtil.ItemCallback<BottomSheetData>() {
    override fun areItemsTheSame(oldItem: BottomSheetData, newItem: BottomSheetData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
    override fun areContentsTheSame(oldItem: BottomSheetData, newItem: BottomSheetData): Boolean {
        return oldItem == newItem
    }

}