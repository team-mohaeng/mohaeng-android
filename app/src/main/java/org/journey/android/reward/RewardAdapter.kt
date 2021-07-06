package org.journey.android.reward


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemRewardChallengeBinding

class RewardAdapter : ListAdapter<RewardData, RewardAdapter.RewardViewHolder>(RewardDiffCallback){
    val rewardList = mutableListOf<RewardData>()

    class RewardViewHolder(val binding : ItemRewardChallengeBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun onBind(rewardData: RewardData){
                    binding.textviewPercentNumber.text = rewardData.percent
                    binding.textviewPercent.text = rewardData.percentUnit
                    binding.textviewChallengePercent.text = rewardData.rewardContent
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