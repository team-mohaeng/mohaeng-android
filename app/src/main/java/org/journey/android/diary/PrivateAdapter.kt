package org.journey.android.diary

import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.journey.android.R
import org.journey.android.databinding.ItemPrivateRecordBinding
import org.journey.android.diary.view.PrivateFragment

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

    class PrivateViewHolder (private val binding : ItemPrivateRecordBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(privateData: PrivateData) {
            binding.textviewPrivateHashtags.text = privateData.textViewHashTags
            binding.textviewPrivateContent.text = privateData.textViewPrivateContent
            binding.textviewPrivateLikeCount.text = privateData.textViewLikeCount
            binding.textviewPrivateNickname.text = privateData.textViewPrivateNickName
            val multi = MultiTransformation<Bitmap>(
                BlurTransformation(25),
                ColorFilterTransformation(Color.argb(80, 0, 0, 0)),
                RoundedCornersTransformation(100, 10)
            )
            Glide.with(itemView)
                .load(privateData.imageViewPrivate)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(binding.imageviewRecyclerviewBackground)
        }
    }
}