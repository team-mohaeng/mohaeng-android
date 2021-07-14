package org.journey.android.reward.util

import androidx.recyclerview.widget.DiffUtil
import org.journey.android.reward.dto.RewardData

object RewardDiffCallback : DiffUtil.ItemCallback<RewardData>() {
    override fun areItemsTheSame(oldItem: RewardData, newItem: RewardData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
    override fun areContentsTheSame(oldItem: RewardData, newItem: RewardData): Boolean {
        return oldItem == newItem
    }

}