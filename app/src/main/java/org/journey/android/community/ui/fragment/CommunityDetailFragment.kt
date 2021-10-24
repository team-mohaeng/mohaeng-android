package org.journey.android.community.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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
import org.journey.android.databinding.FragmentCommunityDetailBinding
import org.journey.android.diary.dto.Emojifaction
import org.journey.android.diary.dto.RequestDiaryEmojiData
import org.journey.android.diary.service.FeedRequestToServer
import org.journey.android.diary.view.postDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        if(postDetail.get("mood")==2)
        {
            binding.imageviewCommunityDetailFront.setImageResource(R.drawable.ic_feel_third)
        }
        else if(postDetail.get("mood")==1)
        {
            binding.imageviewCommunityDetailFront.setImageResource(R.drawable.ic_feel_second)
        }
        else if(postDetail.get("mood")==0)
        {
            binding.imageviewCommunityDetailFront.setImageResource(R.drawable.ic_feel_first)
        }

        Log.d("communityDetail", "${postDetail}")
        Glide.with(this)
            .load(postDetail.get("image"))
            .into(binding.imageviewCommunityDetailBack)

        binding.textviewCommunityDetailNickname.text = postDetail.get("nickname").toString()
        binding.textviewCommunityDetailDate.text = postDetail.get("date").toString()
        binding.textviewCommunityDetailTitle.text = postDetail.get("title").toString()
        binding.textviewCommunityDetailContent.text = postDetail.get("content").toString()

        var emojiList:ArrayList<Emojifaction> = postDetail.get("emoji") as ArrayList<Emojifaction>
        for (i in 0 until emojiList.size) {
            addChipToGroup(emojiList[i].id,emojiList[i].count)
        }
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
                            299,
                            "application/json",
                            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk"
                        )
                    call.enqueue(object : Callback<Unit> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            if (response.isSuccessful) {
                                Toast.makeText(requireContext(), "신고했습니다.", Toast.LENGTH_SHORT).show()
                                reportDialog?.dismiss()
                            } else {
                                if(response.code() == 404){
                                    Toast.makeText(requireContext(), "본인이 작성한 포스트는 신고할 수 없습니다.", Toast.LENGTH_SHORT).show()
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
        }
    }

    fun refreshFragment(){
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
                postDetail.get("id") as Int,
                "application/json",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk",
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
                postDetail.get("id") as Int,
                "application/json",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk",
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
            chip.setOnCloseIconClickListener {
                binding.chipgroupLike.removeView(chip as View)
                likeList.remove(chip.text.toString())
            }

            chip.setOnClickListener {
                if (chip.id == postDetail.get("myemoji")) {
                    deleteEmoji(chip.id)
                    Log.d("chip", chip.id.toString())
                    findNavController().popBackStack()
                }
            }
        }
    }

}