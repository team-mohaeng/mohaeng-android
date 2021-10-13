package org.journey.android.reward.util

import androidx.recyclerview.widget.DiffUtil
import org.journey.android.reward.dto.RewardEntity

object RewardDiffCallback : DiffUtil.ItemCallback<RewardEntity>() {
    override fun areItemsTheSame(oldItem: RewardEntity, newItem: RewardEntity): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
    override fun areContentsTheSame(oldItem: RewardEntity, newItem: RewardEntity): Boolean {
        return oldItem == newItem
    }

}