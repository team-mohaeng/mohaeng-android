package org.journey.android.presentation.main.diary.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateDetailBinding
import org.journey.android.presentation.main.diary.dto.Emojifaction
import org.journey.android.presentation.main.diary.dto.RequestDiaryEmojiData
import org.journey.android.presentation.main.diary.dto.ResponseDiaryPrivateData
import org.journey.android.presentation.main.diary.service.FeedRequestToServer
import org.journey.android.presentation.main.diary.viewmodel.PrivateViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


var postDetail = HashMap<String, Any>()
var refreshCheck = false

@AndroidEntryPoint
class PrivateDetailFragment: Fragment() {
    private lateinit var  binding : FragmentPrivateDetailBinding
    private val viewModel by viewModels<PrivateViewModel>()

    // 공감 이모션 리스트
    var likeList : MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentPrivateDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClickListener(context)

        if(postDetail.get("mood")==2)
        {
            binding.imageviewPrivateDetailFront.setImageResource(R.drawable.ic_feel_third)
        }
        else if(postDetail.get("mood")==1)
        {
            binding.imageviewPrivateDetailFront.setImageResource(R.drawable.ic_feel_second)
        }
        else if(postDetail.get("mood")==0)
        {
            binding.imageviewPrivateDetailFront.setImageResource(R.drawable.ic_feel_first)
        }

        Log.d("privateDetail", "${postDetail}")
        if(postDetail.get("image")==""){
            binding.imageviewPrivateDetailBack.visibility = View.GONE
            binding.viewFeedDetailLine.visibility = View.VISIBLE
        }
        else{
            binding.imageviewPrivateDetailBack.visibility = View.VISIBLE
            binding.viewFeedDetailLine.visibility = View.GONE
            Glide.with(this)
                .load(postDetail.get("image"))
                .into(binding.imageviewPrivateDetailBack)
        }

        binding.textviewPrivateDetailNickname.text = postDetail.get("nickname").toString()
        binding.textviewPrivateDetailDate.text = postDetail.get("date").toString()
        binding.textviewPrivateDetailTitle.text = postDetail.get("title").toString()
        binding.textviewPrivateDetailContent.text = postDetail.get("content").toString()

        var emojiList: ArrayList<Emojifaction> =
            postDetail.get("emoji") as ArrayList<Emojifaction>
        for (i in 0 until emojiList.size) {
            addChipToGroup(emojiList[i].id, emojiList[i].count)
        }

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
                        postDetail.get("id") as Int,
                        "application/json",
                        viewModel.getJWT()
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

    private fun setButtonClickListener(ctxt: Context?){
        with(binding){
            buttonPrivateCancel.setOnClickListener { findNavController().popBackStack() }

            buttonPrivateDetailLike.setOnClickListener{
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
                    putEmojiRetrofit(1)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                diaglogSecond.setOnClickListener {
                    putEmojiRetrofit(2)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogThird.setOnClickListener {
                    putEmojiRetrofit(3)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogFourth.setOnClickListener {
                    putEmojiRetrofit(4)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogFifth.setOnClickListener {
                    putEmojiRetrofit(5)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }
                dialogSixth.setOnClickListener {
                    putEmojiRetrofit(6)
                    alertDialogEmoji.dismiss()
                    refreshFragment()
                }

                alertDialogEmoji.show()
            }

        }
    }

    private fun refreshFragment(){
        setRetrofit(refreshYear, refreshMonth)

        if (getParentFragmentManager() != null) {

            getParentFragmentManager()
                ?.beginTransaction()
                ?.detach(this@PrivateDetailFragment)
                ?.attach(this@PrivateDetailFragment)
                ?.commit()
        }
    }

    private fun putEmojiRetrofit(id:Int){
        val call: Call<Unit> = FeedRequestToServer.writeService
            .putEmoji(
                postDetail.get("id") as Int,
                "application/json",
                viewModel.getJWT(),
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
    private fun deleteEmoji(id: Int){
        Log.d("chip", id.toString())
        val call: Call<Unit> = FeedRequestToServer.writeService
            .deleteEmoji(
                postDetail.get("id") as Int,
                "application/json",
                viewModel.getJWT(),
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


    private fun addChipToGroup(emotion: Int, cnt: Int) {
        if (binding.chipgroupLike.childCount < 6) {
            val chip = Chip(context)
            chip.chipBackgroundColor =
                ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow_b))
            chip.chipStrokeColor = ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow))
            chip.chipStrokeWidth = 2F
            chip.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow2)))
            chip.text = cnt.toString()
            chip.textSize = 12F
            chip.id = emotion
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

            chip.setOnClickListener {
                if(emotion == postDetail.get("myemoji")) {
                    binding.chipgroupLike.removeView(chip as View)
                    likeList.remove(chip.text.toString())
                    deleteEmoji(postDetail.get("myemoji") as Int)
                    refreshFragment()
                }
            }

        }
    }

    private fun setRetrofit(year:String, month:String){
        val call: Call<ResponseDiaryPrivateData> = FeedRequestToServer.service
            .getPrivateDiary(
                year,
                month,
                "application/json",
                viewModel.getJWT()
            )

        call.enqueue(object : Callback<ResponseDiaryPrivateData> {
            override fun onResponse(
                call: Call<ResponseDiaryPrivateData>,
                responsePrivate: Response<ResponseDiaryPrivateData>
            ) {
                if (responsePrivate.isSuccessful) {
                    val dataPrivate = responsePrivate.body()?.data
                    if (dataPrivate != null) {
                        postDetail.put("emoji", dataPrivate.feeds!!.get(postDetail.get("position") as Int)!!.emoji)
                        postDetail.put("myemoji", dataPrivate.feeds!!.get(postDetail.get("position") as Int)!!.myEmoji)
                        Log.d("privateDetail", "${postDetail}")
                        refreshCheck = true
                        findNavController().popBackStack()
                    }

                } else {
                    Log.d("private ClientTest", "Client Error")
                }
            }
            override fun onFailure(call: Call<ResponseDiaryPrivateData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })

    }
}