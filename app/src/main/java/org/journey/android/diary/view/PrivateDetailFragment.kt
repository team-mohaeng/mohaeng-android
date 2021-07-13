package org.journey.android.diary.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateDetailBinding
import org.journey.android.diary.ResponseDiaryPrivateData
import org.journey.android.main.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrivateDetailFragment: Fragment() {

    private lateinit var  binding : FragmentPrivateDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentPrivateDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displaymetricsPrivateDetailFragment = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymetricsPrivateDetailFragment)
        val heightPrivateDetailFragmentDisplay = displaymetricsPrivateDetailFragment.heightPixels * 0.5
        val widthPrivateDetailFragmentDisplay = displaymetricsPrivateDetailFragment.widthPixels * 0.9


        binding.buttonPrivateDelete.setOnClickListener()
        {
            val deleteDialog = activity?.let { it1 -> Dialog(it1) }
            val deleteDialogInflater : LayoutInflater = LayoutInflater.from(activity)
            val mView : View = deleteDialogInflater.inflate(R.layout.private_delete_message_dialog,null)
            val back : Button = mView.findViewById(R.id.button_do_not_delete)
            val window = deleteDialog?.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (deleteDialog != null) {
                deleteDialog.setContentView(mView)
                deleteDialog.create()
                deleteDialog.show()
                deleteDialog.window?.setLayout(widthPrivateDetailFragmentDisplay.toInt(), heightPrivateDetailFragmentDisplay.toInt())
            }
            back.setOnClickListener{
                if (deleteDialog != null)
                {deleteDialog.dismiss()
                    deleteDialog.cancel()}
            }
        }

        val call: Call<ResponseDiaryPrivateData> = RetrofitService.diaryPrivateService
            .getPrivateDiary(32, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiZXA0UmhZcmJUSE9uaHpBUldOVFNTMTpBUEE5MWJIS1pGdkJuUkV1dEEtYzQxSmN6dDBITzVJQkNyMFhzM0VadjFFcUZSVl9jY05semtDbFQtaWxmT3FGTUFWTmFPUFYxaVhIQjIybHhrcHZJRWNTNW4tMjQtZzY2SVR1d0o1aW9aWlJtYVd5R1Q3XzZiUDhlR1BOZHd2SkNwUWxZb1daQlhHVCJ9LCJpYXQiOjE2MjYwODk5OTZ9.fZoVLz1W-C9RNklV0ZPx6yZeysJWfiuOOPhoAlMtG5k")
        call.enqueue(object : Callback<ResponseDiaryPrivateData>{
            override fun onResponse(
                call: Call<ResponseDiaryPrivateData>,
                response: Response<ResponseDiaryPrivateData>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.data
                    if (data != null) {
                        binding.textviewPrivateDetailNickname.text=data.nickname
                        var privateDetailNowDate = data.year + "."
                        privateDetailNowDate = if(data.month.toInt()<10)
                            privateDetailNowDate + "0" + data.month + "."
                        else
                            privateDetailNowDate + data.month + "."
                        if(data.day.toInt()<10)
                            privateDetailNowDate = privateDetailNowDate + "0" + data.day
                        else
                            privateDetailNowDate = privateDetailNowDate + data.day
                        privateDetailNowDate = privateDetailNowDate + "(" + "요일" + ")"
                        binding.textviewPrivateDetailDate.text = privateDetailNowDate
                        binding.textviewPrivateDetailLikeCount.text=data.likeCount.toString()

                        Glide.with(view)
                            .load(data.mainImage)
                            .into(binding.imageviewPrivateDetailBack)
                        Glide.with(view)
                            .load(data.moodImage)
                            .circleCrop()
                            .into(binding.imageviewPrivateDetailFront)


                        val testHashtagString = data.hashtags.joinToString(" ")
                        if(testHashtagString.length==0)
                        {
                            binding.textviewPrivateDetailHashtag.text=testHashtagString
                        }

                        else if(testHashtagString.length in 1..19)
                        {
                            val shortHashtagString = "\n"+testHashtagString
                            binding.textviewPrivateDetailHashtag.isVisible = true
                            binding.textviewPrivateDetailHashtag.text = shortHashtagString
                        }
                        else if(testHashtagString.length>19){
                            val longHashtagString = testHashtagString.substring(0,18) + "\n" + testHashtagString.substring(19, testHashtagString.length-1)
                            binding.textviewPrivateDetailHashtag.isVisible = true
                            binding.textviewPrivateDetailHashtag.text = longHashtagString
                        }

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

            override fun onFailure(call: Call<ResponseDiaryPrivateData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        }
        )

        val testHashtagString = "" // 서버로부터 해시태그를 저장하는 문자열 불러오면 될듯
        if(testHashtagString.isEmpty())
        {
            binding.textviewPrivateDetailHashtag.text=testHashtagString
        }

        else if(testHashtagString.length in 1..19)
        {
            val shortHashtagString = "\n"+testHashtagString
            binding.textviewPrivateDetailHashtag.isVisible = true
            binding.textviewPrivateDetailHashtag.text = shortHashtagString
        }
        else if(testHashtagString.length>19){
            val longHashtagString = testHashtagString.substring(0,18) + "\n" + testHashtagString.substring(19, testHashtagString.length-1)
            binding.textviewPrivateDetailHashtag.isVisible = true
            binding.textviewPrivateDetailHashtag.text = longHashtagString
        }

        val testContentString = "짧은 글"
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