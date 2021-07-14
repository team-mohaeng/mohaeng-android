package org.journey.android.community.view

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.community.ResponseCommunityData
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.main.model.RetrofitService
import org.journey.android.util.OnSwipeTouchListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    val bottomSheetFragment = BottomSheetFragment()
    var happinessStatus = 0
    var todayUploader = 0
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        clickEvent()
        setButtonEvent()
        setUIListener()

        //서버 연결해서 happinessStatus와 몇명이 소확행 피드에 올렸는지 받아옴
        val call: Call<ResponseCommunityData> = RetrofitService.communityService
            .getCommunityDiary(1,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiZXA0UmhZcmJUSE9uaHpBUldOVFNTMTpBUEE5MWJIS1pGdkJuUkV1dEEtYzQxSmN6dDBITzVJQkNyMFhzM0VadjFFcUZSVl9jY05semtDbFQtaWxmT3FGTUFWTmFPUFYxaVhIQjIybHhrcHZJRWNTNW4tMjQtZzY2SVR1d0o1aW9aWlJtYVd5R1Q3XzZiUDhlR1BOZHd2SkNwUWxZb1daQlhHVCJ9LCJpYXQiOjE2MjYwODk5OTZ9.fZoVLz1W-C9RNklV0ZPx6yZeysJWfiuOOPhoAlMtG5k")

        call.enqueue(object: Callback<ResponseCommunityData> {
            override fun onResponse(
                call: Call<ResponseCommunityData>,
                response: Response<ResponseCommunityData>
            ) {
                if(response.isSuccessful)
                {
                    val data = response.body()?.data
                    if (data != null) {
                        happinessStatus = data.hasSmallSatisfaction
                        todayUploader = data.userCount
                        binding.textviewBrowseUser.text = getString(R.string.total_user_format, todayUploader)
                    }
                }
                else{

                }
            }

            override fun onFailure(call: Call<ResponseCommunityData>, t: Throwable) {

            }

        })




    }


    fun setUIListener() {
        with(binding) {
            constraintlayoutFrameCommunity.setOnTouchListener(
                object : OnSwipeTouchListener(context) {
                    override fun onSwipeUp() {
                        bottomSheetFragment.show(childFragmentManager, "bottomsheet")
                    }
                }
            )
        }
    }

    fun setButtonEvent() {
        binding.buttonHappinessWrite.setOnClickListener {
            // Dialog만들기
            val mDialogView =
                LayoutInflater.from(this.context).inflate(R.layout.course_custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
            val alertDialog = mBuilder.create()

            mDialogView.setBackgroundColor(Color.TRANSPARENT)

            //val  mAlertDialog = mBuilder.show()
            val window = alertDialog.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
            val dialogButtons =
                mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
            val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
            val dialogContent = mDialogView.findViewById<TextView>(R.id.textview_dialog_content)

            dialogButtons.visibility = View.GONE
            okButton.visibility = View.VISIBLE

            alertDialog.show()

            if (happinessStatus == 0) {
                dialogTitle.text = "챌린지를 시작해보겠어?"
                okButton.text = "코스 선택하러 가기"
                dialogContent.text = getString(R.string.happiness_popcontent_ver1)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "코스 선택하러 가기 클릭", Toast.LENGTH_SHORT).show()
                }
            } else if (happinessStatus == 1) {
                dialogTitle.text = "이런, 아직 작성할 수 없어!"
                okButton.text = "오늘의 챌린지로 이동하기"
                dialogContent.text = getString(R.string.happiness_popcontent_ver2)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "오늘의 챌린지로 이동하기 클릭", Toast.LENGTH_SHORT).show()
                }
            } else if (happinessStatus == 2) {
                dialogTitle.text = "쟈기, 이미 작성했잖아!"
                okButton.text = "알았어"
                dialogContent.text = getString(R.string.happiness_popcontent_ver3)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "알았어 클릭", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun clickEvent() {
        binding.textviewDiary.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_diaryFirstFragment)
        }
    }
}

