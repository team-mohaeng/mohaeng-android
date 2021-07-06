package org.journey.android.reward

import androidx.recyclerview.widget.DiffUtil

object RewardDiffCallback : DiffUtil.ItemCallback<RewardData>() {
    override fun areItemsTheSame(oldItem: RewardData, newItem: RewardData): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: RewardData, newItem: RewardData): Boolean {
        return oldItem == newItem
    }

}