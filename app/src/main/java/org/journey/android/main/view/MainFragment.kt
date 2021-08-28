package org.journey.android.main.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentMainBinding
import org.journey.android.login.view.userJwt
import org.journey.android.main.dto.ResponseMainModelItem
import org.journey.android.main.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


var userCourseStatus = 0

class MainFragment : BaseFragment<FragmentMainBinding>() {
    var lovePercent = 0
    var today: Int = 0

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        //goToReward()
        showIndexDialog()
        binding.progressbarMain.progress = 50
//        binding.textviewConcentrationPercent.setOnClickListener {
//            //binding.progressbarConcentration.incrementProgressBy(25)
//        }
        //setRetrofit()
    }

    @SuppressLint("ResourceType")
    private fun showIndexDialog(){
        binding.buttonMainReward.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_index_explanation,null)
            dialogView?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogView.setBackgroundResource(R.drawable.selector_index_dialog)
            val builder = AlertDialog.Builder(context)
                .setView(dialogView)
            builder.show()
        }
    }

    private fun setClickListener(){
        with(binding){
            buttonMainChat.setOnClickListener {
                findNavController().navigate(R.id.action_frameFragment_to_chatFragment)
            }
            buttonMainFirst.setOnClickListener {
                findNavController().navigate(R.id.action_frameFragment_to_characterFragment)
            }
        }
    }


    private fun goToReward() {
        binding.buttonMainFirst.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_rewardFragment)
        }
    }
    private fun setMainViewImage(backgroundImageDrawable: Int) {
        //binding.imageviewJourney.
    }

//    private fun setBackground() {
//        when (lovePercent) {
//            in PROGRESSBAR_FIRST_RANGE -> binding.imageviewJourney.setImageResource(R.drawable.imagefirst)
//            in PROGRESSBAR_SECOND_RANGE -> binding.imageviewJourney.setImageResource(R.drawable.imagesecond)
//            in PROGRESSBAR_THIRD_RANGE -> binding.imageviewJourney.setImageResource(R.drawable.imagethird)
//            in PROGRESSBAR_FOURTH_RANGE -> binding.imageviewJourney.setImageResource(R.drawable.imagefour)
//
//        }
//    }

    companion object {
        private val PROGRESSBAR_FIRST_RANGE = 1..25
        private val PROGRESSBAR_SECOND_RANGE = 26..50
        private val PROGRESSBAR_THIRD_RANGE = 51..75
        private val PROGRESSBAR_FOURTH_RANGE = 76..100
    }

//    fun setRetrofit(){
//        RetrofitService.mainService.getMainData(
//            userJwt
//        ).enqueue(object : Callback<ResponseMainModelItem> {
//
//            override fun onFailure(call: Call<ResponseMainModelItem>, t: Throwable) {
//                Log.d("통신 실패", "${t}")
//            }
//
//            override fun onResponse(
//                call: Call<ResponseMainModelItem>,
//                response: Response<ResponseMainModelItem>
//            ) {
//                // 통신 성공
//                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
//                    if (true) {
//                        Log.d("서버 성공", "Home 성공")
//                        Log.d(
//                            "서버", response.body()!!.data.toString()
//                        )
//                        // 쟈니와의 애정도
//                        lovePercent = response.body()!!.data!!.affinity
//                        binding.progressbarConcentration.progress = lovePercent
//                        binding.textviewConcentrationPercent.text = "${lovePercent}%"
//
//                        setBackground()
//
//                        // 코스를 진행하고 있지 않을 때
//                        if(response.body()!!.data!!.situation == 0) {
//                            binding.buttonMainUser.visibility = View.INVISIBLE
//                            binding.textviewMainGreet.text =
//                                "안녕, 쟈기?\n" + "오늘도 당신의 일상에 지독하게 엮어 보려 해"
//                            binding.textviewMainChallenge.text = "나와 함께해보겠어?"
//
//                            userCourseStatus = response.body()!!.data!!.situation
//                        }
//                        else {
//                            // 코스를 진행하고 있을 때
//                            binding.buttonMainUser.visibility = View.VISIBLE
//                            for (i in 0 until response.body()!!.data!!.course!!.challenges.size) {
//                                if (response.body()!!.data!!.course!!.situation == 1) {
//                                    userCourseStatus = response.body()!!.data!!.course!!.id
//
//                                    binding.textviewMainGreet.text =
//                                        response.body()!!.data!!.course!!.description
//                                    binding.textviewMainChallenge.text =
//                                        response.body()!!.data!!.course!!.title
//
//                                    when (response.body()!!.data!!.course!!.property) {
//                                        0 -> {
//                                            binding.buttonMainUser.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_health, 0, 0, 0)
//                                        }
//                                        1 -> {
//                                            binding.buttonMainUser.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_memory,0,0,0)
//                                        }
//                                        2 -> {
//                                            binding.buttonMainUser.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_detect,0,0,0)
//                                        }
//                                        3 -> {
//                                            binding.buttonMainUser.setCompoundDrawablesWithIntrinsicBounds(R.drawable.main_challenge,0,0,0)
//                                        }
//                                    }
//                                }
//
//                            }
//                            // 오늘이 몇일차인지 구하는 부분
//                            for (i in 0 until response.body()!!.data!!.course!!.challenges.size) {
//                                if (response.body()!!.data!!.course!!.challenges[i].situation == 1) {
//                                    today = i+1
//                                    binding.buttonMainUser.text = today.toString() + "일차"
//                                    break
//                                }
//                            }
//                        }
//
//                    }
//                }
//                else {
//                    Log.d("서버 실패", "${response.body()}")
//                    Toast.makeText(context, "만료된 토큰입니다. 우리 아기 고앵이 토큰 하나 더 받아와 쪽-", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }
}