package org.journey.android.community.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.community.data.entity.CommunityPostEntity
import org.journey.android.community.ui.fragment.feedDetail
import org.journey.android.databinding.ItemCommunityRecordBinding

class CommunityPostAdapter(val listener : OnItemClickListener) : RecyclerView.Adapter<CommunityPostAdapter.CommunityPostViewHolder>() {
    var posts = mutableListOf<CommunityPostEntity>()
    interface OnItemClickListener{
        fun itemClick()
    }
    class CommunityPostViewHolder(val binding : ItemCommunityRecordBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityPostViewHolder {
        val binding = ItemCommunityRecordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommunityPostViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CommunityPostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.setVariable(BR.data,post)
        holder.binding.root.setOnClickListener { listener.itemClick()
            feedDetail.put("position", holder.getAdapterPosition()) }
    }
    override fun getItemCount() = posts.size
}