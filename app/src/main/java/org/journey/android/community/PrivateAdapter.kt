package org.journey.android.community

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemPrivateRecordBinding

class PrivateAdapter: RecyclerView.Adapter<PrivateAdapter.PrivateViewHolder>(){
    val privateDiaryList = mutableListOf<PrivateData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PrivateViewHolder {
        val binding = ItemPrivateRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PrivateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrivateAdapter.PrivateViewHolder, position: Int) {
        holder.onBind(privateDiaryList[position])
    }

    override fun getItemCount(): Int = privateDiaryList.size

    class PrivateViewHolder (private val binding : ItemPrivateRecordBinding ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(privateData: PrivateData)
        {
            binding.textviewPrivateHashtagOne.text = privateData.textViewHashTagOne
            binding.textviewPrivateHashtagTwo.text=privateData.textViewHashTagTwo
            binding.textviewPrivateContent.text=privateData.textViewPrivateContent
            binding.textviewPrivateLikeCount.text=privateData.textViewLikeCount
            binding.textviewPrivateNickname.text=privateData.textViewPrivateNickName
        }
    }
}