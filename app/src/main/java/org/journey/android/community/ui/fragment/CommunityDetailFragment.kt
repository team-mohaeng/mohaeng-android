package org.journey.android.community.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import org.journey.android.R
import org.journey.android.community.data.dto.ResponseCommunityFeedDTO
import org.journey.android.databinding.FragmentCommunityDetailBinding
import org.journey.android.diary.dto.Emojifaction
import org.journey.android.diary.dto.RequestDiaryEmojiData
import org.journey.android.diary.dto.ResponseDiaryPrivateData
import org.journey.android.diary.service.FeedRequestToServer
import org.journey.android.diary.view.postDetail
import org.journey.android.diary.view.refreshCheck
import org.journey.android.network.userJWT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var feedDetail = HashMap<String, Any>()

class CommunityDetailFragment : Fragment() {

    private lateinit var  binding : FragmentCommunityDetailBinding

    // 공감 이모션 리스트
    var likeList : MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentCommunityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClickListener(context)
        setRetrofit()
    }

    private fun setButtonClickListener(ctxt: Context?){
        with(binding){
            buttonCommunityCancel.setOnClickListener { findNavController().popBackStack() }

            buttonCommunityDetailLike.setOnClickListener{
                val mDialogViewEmoji =
                    LayoutInflater.from(ctxt).inflate(R.layout.dialog_myfeed_emoji, null)
                val mBuilderEmoji = AlertDialog.Builder(ctxt)
                    .setView(mDialogViewEmoji)
                val alertDialogEmoji = mBuilderEmoji.create()

                mDialogViewEmoji.setBackgroundColor(Color.TRANSPARENT)
                val windowEmoji = alertDialogEmoji.window
                windowEmoji?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val dialogClose = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_close)
                val dialogFirst = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_first)
                val diaglogSecond = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_second)
                val dialogThird = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_third)
                val dialogFourth = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_fourth)
                val dialogFifth = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_fifth)
                val dialogSixth = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_sixth)

                dialogClose.setOnClickListener {
                    alertDialogEmoji.dismiss()
                }
                dialogFirst.setOnClickListener {
//                    addChipToGroup(1,1)
                    putEmojiRetrofit(1)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                diaglogSecond.setOnClickListener {
//                    addChipToGroup(2,1)
                    putEmojiRetrofit(2)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogThird.setOnClickListener {
//                    addChipToGroup(3,1)
                    putEmojiRetrofit(3)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogFourth.setOnClickListener {
//                    addChipToGroup(4,1)
                    putEmojiRetrofit(4)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogFifth.setOnClickListener {
//                    addChipToGroup(5,1)
                    putEmojiRetrofit(5)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogSixth.setOnClickListener {
//                    addChipToGroup(6,1)
                    putEmojiRetrofit(6)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }

                alertDialogEmoji.show()
            }

            imagebuttonCommunityDetailReport.setOnClickListener {
                val reportDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
                val view = layoutInflater.inflate(R.layout.dialog_detail_report, null)
                val window = reportDialog?.window
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val reportBtn = view.findViewById<Button>(R.id.button_dialog_report)

                reportBtn.setOnClickListener {
                    val call: Call<Unit> = FeedRequestToServer.writeService
                        .reportDiary(
                            feedDetail.get("id") as Int,
                            "application/json",
                            userJWT
//                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk"
                        )
                    call.enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful) {
                                Toast.makeText(requireContext(), "신고했습니다.", Toast.LENGTH_SHORT).show()
                                reportDialog?.dismiss()
                            } else {
                                if(response.code() == 404 || response.code() == 401){
                                    Toast.makeText(requireContext(), response.message(), Toast.LENGTH_SHORT).show()
                                }
//                                Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("Report Diary NT Error", "Report Error!")
                        }
                    })
                    reportDialog?.dismiss()
                }

                reportDialog?.setContentView(view)
                reportDialog?.show()

            }

            binding.buttonCommunityDelete.setOnClickListener()
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
//                    deleteDialog.window?.setLayout(
//                        widthCommunityDetailFragmentDisplay.toInt(),
//                        heightCommunityDetailFragmentDisplay.toInt()
//                    )
                }
                closeBtn.setOnClickListener {
                    if (deleteDialog != null) {
                        deleteDialog.dismiss()
                        deleteDialog.cancel()
                    }
                }
                deleteBtn.setOnClickListener {
                    val call: Call<Unit> = FeedRequestToServer.service
                        .deletePrivateDetail(
                            feedDetail.get("id") as Int,
                            "application/json",
                            userJWT
                        )
                    call.enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful) {
                                Toast.makeText(requireContext(), "삭제되었습니다",Toast.LENGTH_SHORT).show()
                                deleteDialog?.dismiss()
                                findNavController().popBackStack()
                            } else {
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Log.d("Delete Diary NT Error", "Delete Error!")
                        }
                    })
                }
            }
        }
    }

    fun refreshFragment(){
        refreshCheck = true
        findNavController().popBackStack()

        if (getParentFragmentManager() != null) {

            getParentFragmentManager()
                ?.beginTransaction()
                ?.detach(this@CommunityDetailFragment)
                ?.attach(this@CommunityDetailFragment)
                ?.commit()
        }
    }

    fun putEmojiRetrofit(id:Int){
        val call: Call<Unit> = FeedRequestToServer.writeService
            .putEmoji(
                feedDetail.get("id") as Int,
                "application/json",
                userJWT,
//                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk",
                RequestDiaryEmojiData(
                    emojiId = id
                )
            )
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
//                    Toast.makeText(requireContext(), "삭제되었습니다",Toast.LENGTH_SHORT).show()
                } else {
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("Emoji Diary NT Error", "Emoji Error!")
            }
        })
    }
    fun deleteEmoji(id: Int){
        Log.d("chip", id.toString())
        val call: Call<Unit> = FeedRequestToServer.writeService
            .deleteEmoji(
                feedDetail.get("id") as Int,
                "application/json",
                userJWT,
//                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk",
                RequestDiaryEmojiData(
                    emojiId = id
                )
            )
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                } else {
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d("Emoji Delete NT Error", "Emoji DeleteError!")
            }
        })
    }

    fun addChipToGroup(emotion: Int, cnt: Int) {
        if (binding.chipgroupLike.childCount < 6) {
            val chip = Chip(context)
            chip.chipBackgroundColor =
                ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow_b))
            chip.chipStrokeColor = ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow))
            chip.chipStrokeWidth = 2F
            chip.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow2)))
            chip.text = cnt.toString()
            chip.textSize = 12F
            when(emotion){
                1-> chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_emoji_tears)
                2-> chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_emoji_cong)
                3-> chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_emoji_medal)
                4-> chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_emoji_good)
                5-> chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_emoji_twinkle)
                6-> chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_emoji_lucky)
            }

            chip.isChipIconVisible = true
            chip.iconStartPadding = 30F
            chip.iconEndPadding = 5F


            chip.isClickable = true
            chip.isCheckable = false
            likeList.add(chip.text.toString())
            binding.chipgroupLike.addView(chip as View)

//            if(emotion == feedDetail.get("myemoji")){
//                chip.isCloseIconVisible = true
//            }

            chip.setOnClickListener {
                if(emotion == feedDetail.get("myemoji")) {
                    binding.chipgroupLike.removeView(chip as View)
                    likeList.remove(chip.text.toString())
                    deleteEmoji(feedDetail.get("myemoji") as Int)
                    refreshFragment()
                }
            }

//            chip.setOnClickListener {
//                Log.d("chip1", "${chip.chipIcon} ${feedDetail.get("myemoji")}")
//                var emojisrc = ""
//                when(feedDetail.get("myemoji")){
//                    1 -> emojisrc = "android.graphics.drawable.VectorDrawable@b55861"
//                    2 -> emojisrc = "android.graphics.drawable.VectorDrawable@d93f4b3"
//                    3 -> emojisrc = "android.graphics.drawable.VectorDrawable@7b397a7"
//                    4 -> emojisrc = "android.graphics.drawable.VectorDrawable@ed4c232"
//                    5 -> emojisrc = "android.graphics.drawable.VectorDrawable@db5ef9"
//                    6 -> emojisrc = "android.graphics.drawable.VectorDrawable@dc11bbb"
//                }
//                Log.d("chip11", chip.chipIcon.toString() +"${emojisrc}")
//                if(chip.chipIcon.toString() == emojisrc){
//                    Log.d("chip2", "${chip.chipIcon} ${feedDetail.get("myemoji")}")
//                }
//                if (chip.id == feedDetail.get("myemoji")) {
//                    Log.d("chip", chip.id.toString())
//                    refreshFragment()
//                    deleteEmoji(chip.id)
//                }
//            }
        }
    }

    fun setRetrofit(){
        val call: Call<ResponseCommunityFeedDTO> = FeedRequestToServer.service
            .getCommunityDiary(
                "application/json",
                userJWT
//                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk"
            )

        call.enqueue(object : Callback<ResponseCommunityFeedDTO> {
            override fun onResponse(
                call: Call<ResponseCommunityFeedDTO>,
                responsePrivate: Response<ResponseCommunityFeedDTO>
            ) {
                if (responsePrivate.isSuccessful) {
                    val dataDetail = responsePrivate.body()?.data

                    if (dataDetail != null) {
                        var detailMood = dataDetail.feeds.get(feedDetail.get("position") as Int).mood
                        if(detailMood==2)
                        {
                            binding.imageviewCommunityDetailFront.setImageResource(R.drawable.ic_feel_third)
                        }
                        else if(detailMood==1)
                        {
                            binding.imageviewCommunityDetailFront.setImageResource(R.drawable.ic_feel_second)
                        }
                        else if(detailMood==0)
                        {
                            binding.imageviewCommunityDetailFront.setImageResource(R.drawable.ic_feel_first)
                        }

                        if(dataDetail?.feeds!!.get(feedDetail.get("position") as Int)!!.isDelete){
                            binding.buttonCommunityDelete.visibility = View.VISIBLE
                        }
                        else{
                            binding.buttonCommunityDelete.visibility = View.INVISIBLE
                        }

                        if(dataDetail?.feeds!!.get(feedDetail.get("position") as Int)!!.isReport){
                            binding.imagebuttonCommunityDetailReport.visibility = View.VISIBLE
                        }
                        else{
                            binding.imagebuttonCommunityDetailReport.visibility = View.INVISIBLE
                        }

                        binding.textviewCommunityDetailNickname.text = dataDetail.feeds.get(feedDetail.get("position") as Int).nickname
                        binding.textviewCommunityDetailDate.text = "${dataDetail.feeds.get(feedDetail.get("position") as Int).month}월 ${dataDetail.feeds.get(feedDetail.get("position") as Int).date}일"
                        binding.textviewCommunityDetailTitle.text = dataDetail.feeds.get(feedDetail.get("position") as Int).course + " " +
                                dataDetail.feeds.get(feedDetail.get("position") as Int).challenge.toString() + "일차"
                        binding.textviewCommunityDetailContent.text = dataDetail.feeds.get(feedDetail.get("position") as Int).content

                        feedDetail.put("emoji", dataDetail.feeds.get(feedDetail.get("position") as Int).emoji)
                        feedDetail.put("myemoji", dataDetail.feeds.get(feedDetail.get("position") as Int).myEmoji)
                        feedDetail.put("id", dataDetail.feeds.get(feedDetail.get("position") as Int).postId)

                        var emojiList = dataDetail.feeds.get(feedDetail.get("position") as Int).emoji
                        for (i in 0 until emojiList.size) {
                            addChipToGroup(emojiList[i].id,emojiList[i].count)
                        }

                        var imageDetail = dataDetail.feeds.get(feedDetail.get("position") as Int).image
                        if(imageDetail == ""){
                            binding.imageviewCommunityDetailBack.visibility = View.GONE
                            binding.viewCommunityDetailLine.visibility = View.VISIBLE
                        }
                        else{
                            binding.imageviewCommunityDetailBack.visibility = View.VISIBLE
                            binding.viewCommunityDetailLine.visibility = View.GONE
                            Glide.with(this@CommunityDetailFragment)
                                .load(imageDetail)
                                .into(binding.imageviewCommunityDetailBack)
                            Log.d("img", "$imageDetail $feedDetail")
                        }
                        Log.d("communityDetail", "${dataDetail.feeds.get(feedDetail.get("position") as Int)}")
                    }

                } else {
                    Log.d("communityDetail", "Client Error")
                }
            }
            override fun onFailure(call: Call<ResponseCommunityFeedDTO>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
    }

}