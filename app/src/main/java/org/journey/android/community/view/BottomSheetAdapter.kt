package org.journey.android.community.view

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.journey.android.R
import org.journey.android.community.dto.BottomSheetData
import org.journey.android.community.dto.BottomSheetDiffCallback
import org.journey.android.databinding.ItemCommunityRecordBinding

class BottomSheetAdapter(val listener: OnItemClickListener) : ListAdapter<BottomSheetData, BottomSheetAdapter.BottomSheetViewHolder>(
    BottomSheetDiffCallback
) {
    val bottomList = mutableListOf<BottomSheetData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomSheetViewHolder {
        val binding = ItemCommunityRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return BottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        holder.bind(bottomList[position])
        holder.itemView.isSelected = true
    }

    override fun getItemCount(): Int= bottomList.size

    class BottomSheetViewHolder(val binding : ItemCommunityRecordBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(bottomSheetData: BottomSheetData){
                    binding.textviewTitle.text = bottomSheetData.title
                    //binding.textviewSecondTags.text= bottomSheetData.second_tags
                    binding.textviewRecordContent.text = bottomSheetData.diary
                    binding.textviewUserId.text = bottomSheetData.user_id
//                    binding.textviewCountPrefer.text = bottomSheetData.user_prefer.toString()
//                    if(bottomSheetData.has_like)
//                    {
//                        binding.buttonUserPrefer.setTextColor(Color.parseColor(R.color.journey_pink2.toString()))
//                        binding.buttonUserPrefer.setBackgroundResource(R.drawable.ic_icnheartfull)
//                    }
//                    else{
//                        binding.buttonUserPrefer.setBackgroundResource(R.drawable.ic_diary_private_heart)
//                    }
                    val multiEffect = MultiTransformation<Bitmap>(
                        BlurTransformation(25),
                        ColorFilterTransformation(Color.argb(80, 0, 0, 0)),
                        RoundedCornersTransformation(100, 0)
                    )
                    Glide.with(itemView)
                        .load(bottomSheetData.main_image)
                        .apply(RequestOptions.bitmapTransform(multiEffect))
                        .into(binding.imageviewCommunityItemBackground)

                    itemView.setOnClickListener {
                        itemView.findNavController().navigate(R.id.action_communityFragment_to_communityDetailFragment)
                    }
                }
            }


    interface OnItemClickListener{
        fun itemClickListener(view : View, position: Int)
    }

}