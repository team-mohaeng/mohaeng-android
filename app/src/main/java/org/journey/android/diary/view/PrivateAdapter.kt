package org.journey.android.diary.view

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.journey.android.databinding.ItemPrivateRecordBinding
import org.journey.android.diary.dto.PrivateData


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

    override fun onBindViewHolder(holder: PrivateViewHolder, position: Int) {
        holder.onBind(privateDiaryList[position])
    }

    override fun getItemCount(): Int = privateDiaryList.size

    class PrivateViewHolder(private val binding: ItemPrivateRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(privateData: PrivateData) {
            binding.textviewPrivateTitle.text = privateData.textViewHashTags
            binding.textviewPrivateContent.text = privateData.textViewPrivateContent
//            binding.textviewPrivateLikeCount.text = privateData.textViewLikeCount
            binding.textviewPrivateNickname.text = privateData.textViewPrivateNickName
            val multi = MultiTransformation<Bitmap>(
//                BlurTransformation(25),
//                ColorFilterTransformation(Color.argb(80, 0, 0, 0)),
                RoundedCornersTransformation(4, 0)
            )
            Glide.with(itemView)
                .load(privateData.imageViewPrivate)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(binding.imageviewRecyclerviewBackground)
//            if(privateData.hasLike)
//            {
//                binding.buttonImagePrivate.setTextColor(ContextCompat.getColor(binding.buttonImagePrivate.context,R.color.journey_pink2))
//                binding.buttonImagePrivate.setBackgroundResource(R.drawable.ic_icnheartfull)
//            }

            itemView.setOnClickListener {
                postDetailId=privateData.postId
                itemView.findNavController().navigate(R.id.action_privateFragment_to_privateDetailFragment)
            }
        }
    }
}