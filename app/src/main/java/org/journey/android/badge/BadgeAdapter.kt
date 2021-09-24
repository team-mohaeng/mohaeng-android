package org.journey.android.badge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemBadgeBinding

class BadgeAdapter : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {
    var badgeList = mutableListOf<BadgeEntity>()
    class BadgeViewHolder(val binding : ItemBadgeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val binding = ItemBadgeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BadgeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        val badge = badgeList[position]
        holder.binding.setVariable(BR.data,badge)
    }

    override fun getItemCount() = badgeList.size

}