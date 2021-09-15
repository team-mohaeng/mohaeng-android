package org.journey.android.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemCommunityRecordBinding

class CommunityPostAdapter : RecyclerView.Adapter<CommunityPostAdapter.CommunityPostViewHolder>() {
    var posts = mutableListOf<CommunityPostEntity>()
    class CommunityPostViewHolder(val binding : ItemCommunityRecordBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityPostViewHolder {
        val binding = ItemCommunityRecordBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommunityPostViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CommunityPostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.setVariable(BR.data,post)
    }
    override fun getItemCount() = posts.size
}