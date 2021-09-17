package org.journey.android.diary.view

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateDetailBinding
import org.journey.android.diary.dto.ResponseDiaryDislikeData
import org.journey.android.diary.dto.ResponseDiaryLikeData
import org.journey.android.diary.dto.ResponseDiaryPrivateDetailData
import org.journey.android.login.view.userJwt
import org.journey.android.main.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var postDetailId = 0
class PrivateDetailFragment: Fragment() {

    private lateinit var  binding : FragmentPrivateDetailBinding

    // 공감 이모션 리스트
    var likeList : MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentPrivateDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var journeyMood = 0
        val displaymetricsPrivateDetailFragment = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(
            displaymetricsPrivateDetailFragment
        )
        val heightPrivateDetailFragmentDisplay =
            displaymetricsPrivateDetailFragment.heightPixels * 0.5
        val widthPrivateDetailFragmentDisplay =
            displaymetricsPrivateDetailFragment.widthPixels * 0.9
        var postNumCurrentPage = 0
        val call: Call<ResponseDiaryPrivateDetailData> = RetrofitService.diaryPrivateDetailService
            .getPrivateDetailDiary(
                postDetailId,
                userJwt

            )
        call.enqueue(object : Callback<ResponseDiaryPrivateDetailData> {
            override fun onResponse(
                call: Call<ResponseDiaryPrivateDetailData>,
                responseDetail: Response<ResponseDiaryPrivateDetailData>
            ) {
                if (responseDetail.isSuccessful) {
                    val data = responseDetail.body()?.data
                    if (data != null) {
                        postNumCurrentPage = data.postId
                        binding.textviewPrivateDetailNickname.text = data.nickname
                        var privateDetailNowDate = data.year + "."
                        privateDetailNowDate = privateDetailNowDate + data.month + "."
                        privateDetailNowDate = privateDetailNowDate + data.day
                        privateDetailNowDate = privateDetailNowDate + "(" + data.week + ")"
                        binding.textviewPrivateDetailDate.text = privateDetailNowDate
                        binding.buttonPrivateDetailLike.text=data.likeCount.toString()

                        val multi = MultiTransformation<Bitmap>(
                            GranularRoundedCorners(50F,50F,0F,0F)
                        )

                        Glide.with(view)
                            .load(data.mainImage)
                            .apply(RequestOptions.bitmapTransform(multi))
                            .into(binding.imageviewPrivateDetailBack)

                        journeyMood=data.mood
                        if(journeyMood==0)
                            binding.imageviewPrivateDetailFront.setImageResource(R.drawable.ic_diary_journey_bad_face)
                        else if(journeyMood==1)
                            binding.imageviewPrivateDetailFront.setImageResource(R.drawable.ic_diary_journey_soso_face)
                        else if(journeyMood==2)
                            binding.imageviewPrivateDetailFront.setImageResource(R.drawable.ic_diary_journey_good_face)


//                        val testHashtagString = data.hashtags.joinToString(" ")
//                        if (testHashtagString.isEmpty()) {
//                            binding.textviewPrivateDetailHashtag.text = testHashtagString
//                        } else if (testHashtagString.length in 1..19) {
//                            val shortHashtagString = "\n" + testHashtagString
//                            binding.textviewPrivateDetailHashtag.isVisible = true
//                            binding.textviewPrivateDetailHashtag.text = shortHashtagString
//                        } else if (testHashtagString.length > 19) {
//                            val longHashtagString = testHashtagString.substring(0, 18) + "\n" + testHashtagString.substring(19, testHashtagString.length - 1)
//                            binding.textviewPrivateDetailHashtag.isVisible = true
//                            binding.textviewPrivateDetailHashtag.text = longHashtagString
//                        }

                        val testContentString = data.content
                        if(testContentString.length>=18)
                        {
                            val longContentString = testContentString.substring(0,16)+"\n"+testContentString.substring(17, testContentString.length-1)
                            binding.textviewPrivateDetailContent.text=longContentString
                        }
                        else{
                            binding.textviewPrivateDetailContent.text = testContentString+"\n"
                        }
                    }
                }
                else{
                    Log.d("ClientTest", "Client Error")
                }
            }
            override fun onFailure(call: Call<ResponseDiaryPrivateDetailData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        }
        )

        binding.buttonPrivateDelete.setOnClickListener()
        {
            val deleteDialog = activity?.let { it1 -> Dialog(it1) }
            val deleteDialogInflater: LayoutInflater = LayoutInflater.from(activity)
            val mView: View =
                deleteDialogInflater.inflate(R.layout.dialog_detail_delete, null)
            val deleteBtn: Button = mView.findViewById(R.id.button_dialog_delete)
            val closeBtn: Button = mView.findViewById(R.id.button_dialog_close)
            val window = deleteDialog?.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (deleteDialog != null) {
                deleteDialog.setContentView(mView)
                deleteDialog.create()
                deleteDialog.show()
                deleteDialog.window?.setLayout(
                    widthPrivateDetailFragmentDisplay.toInt(),
                    heightPrivateDetailFragmentDisplay.toInt()
                )
            }
            closeBtn.setOnClickListener {
                if (deleteDialog != null) {
                    deleteDialog.dismiss()
                    deleteDialog.cancel()
                }
            }
            deleteBtn.setOnClickListener {
                val call: Call<Unit> = RetrofitService.diaryDeleteService
                    .deleteDiary(
                        postNumCurrentPage,
                        userJwt
                    )
                call.enqueue(object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext(), "삭제되었습니다",Toast.LENGTH_SHORT).show()
                        } else {
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        Log.d("Delete Diary NT Error", "Delete Error!")
                    }
                })
            }
        }

        binding.buttonPrivateDetailLike.setOnClickListener{
            addChipToGroup(3)
        }

        binding.buttonPrivateCancel.setOnClickListener {
            findNavController().navigate(R.id.action_privateDetailFragment_to_privateFragment)
        }
    }

    fun addChipToGroup(emotion: Int) {
        if (binding.chipgroupLike.childCount < 6) {
            val chip = Chip(context)
            chip.chipBackgroundColor =
                ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow_b))
            chip.chipStrokeColor = ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow))
            chip.chipStrokeWidth = 2F
            chip.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow2)))
            chip.text = emotion.toString()
            chip.textSize = 12F
            chip.chipIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_launcher_background)
            chip.isChipIconVisible = true
//            chip.chipIconSize = 20F
            chip.iconStartPadding = 30F
            chip.iconEndPadding = 5F


//            chip.isCloseIconVisible = false
//            chip.closeIcon =
//                ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary_hash_tag_close)
//            chip.closeIconSize = 36F
//            chip.closeIconStartPadding = -10F
//            chip.closeIconEndPadding = 30F
//            chip.closeIconTint =
//                ColorStateList.valueOf(resources.getColor(R.color.journey_pink))
            chip.isClickable = true
            chip.isCheckable = false
            likeList.add(chip.text.toString())
            binding.chipgroupLike.addView(chip as View)
            chip.setOnCloseIconClickListener {
                binding.chipgroupLike.removeView(chip as View)
                likeList.remove(chip.text.toString())
            }
        }
//        else if(binding.chipgroupLike.childCount==6)
//        {
//            val displaymetrics = DisplayMetrics()
//            requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
//            val height = displaymetrics.heightPixels * 0.4
//            val width = displaymetrics.widthPixels * 0.9
//            val alertDialog = activity?.let { it1 -> Dialog(it1) }
//            val alertDialogInflater : LayoutInflater = LayoutInflater.from(activity)
//            val mView : View = alertDialogInflater.inflate(R.layout.diary_hashtag_count_dialog,null)
//
//            val window = alertDialog?.window
//            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            val buttonConfirm:Button = mView.findViewById(R.id.button_confirm)
//            if (alertDialog != null) {
//                alertDialog.setContentView(mView)
//                alertDialog.create()
//                alertDialog.show()
//            }
//            buttonConfirm.setOnClickListener{
//                if (alertDialog != null)
//                {alertDialog.dismiss()
//                    alertDialog.cancel()}
//            }
//        }
    }

}