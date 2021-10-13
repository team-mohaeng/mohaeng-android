package org.journey.android.reward.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemRewardChallengeBinding

import org.journey.android.reward.dto.RewardEntity
import org.journey.android.reward.util.RewardDiffCallback

class RewardAdapter : ListAdapter<RewardEntity, RewardAdapter.RewardViewHolder>(RewardDiffCallback){
    val rewardList = mutableListOf<RewardEntity>()
    class RewardViewHolder(val binding : ItemRewardChallengeBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun onBind(rewardEntity: RewardEntity){
                    binding.textviewPercentNumber.text = rewardEntity.percent
                    binding.textviewPercent.text = rewardEntity.percentUnit
                    binding.textviewChallengePercent.text = rewardEntity.rewardContent
                }
            }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RewardViewHolder {
        val binding = ItemRewardChallengeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return RewardViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RewardViewHolder, position: Int) {
        holder.onBind(rewardList[position])
        holder.itemView.isSelected = true
    }
    override fun getItemCount(): Int = rewardList.size
}