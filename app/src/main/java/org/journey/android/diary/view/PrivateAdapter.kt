package org.journey.android.diary.view

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.journey.android.R
import org.journey.android.databinding.ItemPrivateRecordBinding
import org.journey.android.diary.dto.PrivateData
import java.util.*


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
            binding.textviewPrivateTitle.text = "${privateData.course} ${privateData.challenge}일차"
            binding.textviewPrivateContent.text = privateData.content
            binding.textviewPrivateNickname.text = privateData.nickname
            binding.textviewPrivateDate.text = "${privateData.month}월 ${privateData.date}일"

            val privateInstance = Calendar.getInstance()
            val privateNowYear = privateInstance.get(Calendar.YEAR).toString()
            var privateNowMonth = (privateInstance.get(Calendar.MONTH) + 1).toString()
            if (privateNowMonth.toInt() < 10)
                privateNowMonth = "0$privateNowMonth"
            if ((privateNowYear.equals(privateData.year)) and (privateNowMonth.equals(privateData.month)))
                binding.imageviewPrivateToday.visibility = View.VISIBLE
            else
                binding.imageviewPrivateToday.visibility = View.INVISIBLE

            val multi = MultiTransformation<Bitmap>(
//                BlurTransformation(25),
//                ColorFilterTransformation(Color.argb(80, 0, 0, 0)),
                RoundedCornersTransformation(4, 0)
            )
            Glide.with(itemView)
                .load(privateData.image)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(binding.imageviewRecyclerviewBackground)

            itemView.setOnClickListener {
//                postDetailId=privateData.postId
//                postDetailMood = privateData.mood
//                postDetailImage = privateData.image
//                postDetailTitle = "${privateData.course} ${privateData.challenge}일차"
//                postDetailContent = privateData.content
                postDetail.put("id",privateData.postId)
                postDetail.put("mood",privateData.mood)
                postDetail.put("image",privateData.image)
                postDetail.put("title","${privateData.course} ${privateData.challenge}일차")
                postDetail.put("content",privateData.content)
                postDetail.put("nickname",privateData.nickname)
                postDetail.put("date","${privateData.month}월 ${privateData.date}일")
                postDetail.put("emoji", privateData.emoji)
                postDetail.put("myemoji", privateData.myEmoji)
                postDetail.put("isDelete", privateData.isDelete)

                Log.d("private",privateData.toString() )

                itemView.findNavController().navigate(R.id.action_privateFragment_to_privateDetailFragment)
            }
        }
    }
}