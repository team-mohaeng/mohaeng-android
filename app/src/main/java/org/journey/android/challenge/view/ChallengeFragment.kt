package org.journey.android.challenge.view

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.challenge.data.ResponseChallengeData
import org.journey.android.challenge.data.ResponseStampData
import org.journey.android.databinding.FragmentChallengeBinding
import org.journey.android.login.view.userJwt
import org.journey.android.main.RetrofitService
import org.journey.android.main.dto.ResponseMainModelItem
import org.journey.android.main.view.userCourseStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 챌린지 시작 여부 알려주는 변수
var hasCourse = userCourseStatus

// challenge situation
var challengeStatus = 0
//var check = 0

class ChallengeFragment : BaseFragment<FragmentChallengeBinding>() {
    
    //private var _binding: FragmentChallengeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val binding get() = _binding!!

    // 인증 개수 저장하는 변수
    private var stampNumber = 3

    // 완료한 미션 개수를 저장하는 변수
    private var stampComplete = 0

    // 코스 완료 여부 알려주는 변수
    private var courseEnd = false

    // 코스 전체 챌린지 개수
    private var courseDays = 1

    // 현재 코스 번호
    private var courseNumber = 1

    // courseId
    private var userCourseId = 0

    // challengeId
    private var userChallengeId = 0

    // journey msg
    private var journeyMsg = ""

    // journey end msg
    private var journeyEndMsg = ""

    // popup msg array
    private lateinit var userMents : List<String>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*
        if(check == 0){
            binding.constraintlayoutChallengeGone.visibility = View.VISIBLE
            binding.constraintlayoutChallengeOngoing.visibility = View.GONE
            check = 1
        }
        else{
            setRetrofitMain()
        }*/
        setRetrofitMain()

        initChallengeType(binding.imagebuttonFirstFirst)
        initChallengeType(binding.imagebuttonSecondFirst)
        initChallengeType(binding.imagebuttonSecondSecond)
        initChallengeType(binding.imagebuttonThirdFirst)
        initChallengeType(binding.imagebuttonThirdSecond)
        initChallengeType(binding.imagebuttonThirdThird)

    }

    var challengeType = 0

    fun initChallengeType(btnStamp: ImageButton){
        when(challengeType){
            0 -> btnStamp.setImageResource(R.drawable.stamp_health_no)
            1 -> btnStamp.setImageResource(R.drawable.stamp_memory_no)
            2 -> btnStamp.setImageResource(R.drawable.stamp_detect_no)
            3 -> btnStamp.setImageResource(R.drawable.stamp_challenge_no)
        }
    }

    // 챌린지 시작 여부에 따라 다른 뷰를 보여줌
    fun selectChallengeView() {
        //setRetrofit(courseId)

        Log.d("challenge_course",hasCourse.toString())

        if (hasCourse!=0) {

            binding.constraintlayoutChallengeGone.visibility = View.GONE
            binding.constraintlayoutChallengeOngoing.visibility = View.VISIBLE

            binding.textviewJourneyTalk.text = journeyMsg

            var height: Int = 0
            var width: Int = 0

            // 챌린지 완료 후 나타나는 Dialog만들기
            val mDialogViewEnd =
                LayoutInflater.from(this.context).inflate(R.layout.challenge_custom_dialog, null)
            val mBuilderEnd = AlertDialog.Builder(this.context)
                .setView(mDialogViewEnd)
            val alertDialogEnd = mBuilderEnd.create()

            mDialogViewEnd.setBackgroundColor(Color.TRANSPARENT)
            val windowEnd = alertDialogEnd.window
            windowEnd?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialogTitleEnd = mDialogViewEnd.findViewById<TextView>(R.id.textview_dialog_title)
            val writeButtonEnd = mDialogViewEnd.findViewById<AppCompatButton>(R.id.button_dialog_write)
            val laterButtonEnd = mDialogViewEnd.findViewById<AppCompatButton>(R.id.button_dialog_later)
            val imageEnd = mDialogViewEnd.findViewById<ImageView>(R.id.imageview_dialog_image)
            
            // 코스 완료 후 나타나는 dialog 만들기
            val mDialogViewCourse = LayoutInflater.from(this.context).inflate(R.layout.challenge_course_dialog, null)
            val mBuilderCourse = AlertDialog.Builder(this.context)
                .setView(mDialogViewCourse)
            val alertDialogCourse = mBuilderCourse.create()

            mDialogViewCourse.setBackgroundColor(Color.TRANSPARENT)
            val windowCourse = alertDialogCourse.window
            windowCourse?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialogTitleCourse = mDialogViewCourse.findViewById<TextView>(R.id.textview_dialog_title)
            val writeButtonCourse = mDialogViewCourse.findViewById<AppCompatButton>(R.id.button_dialog_write)
            val laterButtonCourse = mDialogViewCourse.findViewById<AppCompatButton>(R.id.button_dialog_later)
            val imageCourse = mDialogViewCourse.findViewById<ImageView>(R.id.imageview_dialog_image)


            val ctxt = this.context
            // Dialog 만들기
            val mDialogView = LayoutInflater.from(this.context).inflate(R.layout.course_custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
            val alertDialog = mBuilder.create()

            mDialogView.setBackgroundColor(Color.TRANSPARENT)

            //val  mAlertDialog = mBuilder.show()
            val window = alertDialog.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
            val dialogButtons = mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
            val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
            val dialog_image = mDialogView.findViewById<ImageView>(R.id.imageview_dialog_image)
            val dialog_ment = mDialogView.findViewById<TextView>(R.id.textview_dialog_content)


            // 인증하기 버튼 클릭 이벤트
            fun checkMission(btnStamp: ImageButton) {
                //setRetrofit()
                dialog_image.setImageResource(R.drawable.challenge_stamp_journey)

                dialogTitle.text = "인증하기"
                okButton.text = "완료"
                dialog_ment.text = userMents[stampComplete]

                dialogButtons.visibility = View.GONE
                okButton.visibility = View.VISIBLE

                alertDialog.show()

                okButton.setOnClickListener {
                    setStampRetrofit(userCourseId, userChallengeId)
                    alertDialog.dismiss()

                    when(challengeType){
                        0 -> btnStamp.setImageResource(R.drawable.stamp_health)
                        1 -> btnStamp.setImageResource(R.drawable.stamp_memory)
                        2 -> btnStamp.setImageResource(R.drawable.stamp_detect)
                        3 -> btnStamp.setImageResource(R.drawable.stamp_challenge)
                    }
                    stampComplete = stampComplete + 1
                    Log.d("stamp 확인", stampComplete.toString())

                    if(courseDays == courseNumber){
                        courseEnd = true
                    }

                    // 스탬프 다  인증 완료하면 완료 팝업 등
                    if(stampComplete == stampNumber){
                        binding.textviewJourneyTalk.text = journeyEndMsg
                        binding.textviewFirstExplain.text = "오늘의 챌린지 성공!\n" + "내일 새로운 챌린지로 다시 만나요!"
                        binding.textviewSecondExplain.text = "오늘의 챌린지 성공!\n" + "내일 새로운 챌린지로 다시 만나요!"
                        binding.textviewThirdExplain.text = "오늘의 챌린지 성공!\n" + "내일 새로운 챌린지로 다시 만나요!"
                        when(stampNumber){
                            1 -> binding.imageviewBackFirst.visibility = View.VISIBLE
                            2 -> binding.imageviewBackSecond.visibility = View.VISIBLE
                            3 -> binding.imageviewBackThird.visibility = View.VISIBLE
                        }

                        if(courseEnd){
                            dialogTitleCourse.text = "뽀득뽀득 세균 퇴치\n" + " 코스 완료"
                            writeButtonCourse.setOnClickListener {
                                alertDialogCourse.dismiss()
                                //Toast.makeText(this.context, "작성할게! 클릭", Toast.LENGTH_SHORT).show()
                            }
                            laterButtonCourse.setOnClickListener {
                                alertDialogCourse.dismiss()
                                //Toast.makeText(this.context, "나중에 클릭", Toast.LENGTH_SHORT).show()
                            }
                            lateinit var mAnim1: Animation
                            mAnim1 = AnimationUtils.loadAnimation(ctxt, R.anim.challenge_image_animation)
                            mAnim1.setInterpolator(ctxt, android.R.anim.accelerate_interpolator)
                            alertDialogCourse.show()
                            imageCourse?.startAnimation(mAnim1)
                        }
                        else{
                            writeButtonEnd.setOnClickListener {
                                alertDialogEnd.dismiss()
                                //Toast.makeText(this.context, "작성할게! 클릭", Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_challengeFragment_to_diaryFirstFragment)
                            }
                            laterButtonEnd.setOnClickListener {
                                alertDialogEnd.dismiss()
                                //Toast.makeText(this.context, "나중에 클릭", Toast.LENGTH_SHORT).show()
                            }
                            lateinit var mAnim1: Animation
                            mAnim1 = AnimationUtils.loadAnimation(ctxt, R.anim.challenge_image_animation)
                            mAnim1.setInterpolator(ctxt, android.R.anim.accelerate_interpolator)
                            alertDialogEnd.show()
                            imageEnd?.startAnimation(mAnim1)
                        }
                    }
                    else{
                        binding.textviewFirstExplain.text = "아이콘을 터치해 인증을 완료할 수 있어요\n" + "자정 전까지 오늘의 챌린지를 성공해보세요!"
                        binding.textviewSecondExplain.text = "아이콘을 터치해 인증을 완료할 수 있어요\n" + "자정 전까지 오늘의 챌린지를 성공해보세요!"
                        binding.textviewThirdExplain.text = "아이콘을 터치해 인증을 완료할 수 있어요\n" + "자정 전까지 오늘의 챌린지를 성공해보세요!"
                    }
                }
                btnStamp.isEnabled = false
            }

            // 미션 개수에 따라 다르게 보여줌
            if(stampNumber == 1) {
                when(stampComplete){
                    0->{
                        setStampNo(binding.imagebuttonFirstFirst)
                    }
                    1->{
                        setStampYes(binding.imagebuttonFirstFirst)
                    }
                }

                binding.constraintlayoutSubFirst.visibility = View.VISIBLE
                binding.constraintlayoutSubSecond.visibility = View.GONE
                binding.constraintlayoutSubThird.visibility = View.GONE

                height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    140F,
                    resources.displayMetrics
                ).toInt()
                width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    140F,
                    resources.displayMetrics
                ).toInt()

                height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    140F,
                    resources.displayMetrics
                ).toInt()
                width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    120F,
                    resources.displayMetrics
                ).toInt()


                // 스탬프 누르면 인증 팝업 등장
                if(challengeStatus == 1){
                    binding.imagebuttonFirstFirst.setOnClickListener {
                        checkMission(binding.imagebuttonFirstFirst)
                    }
                }
            }
            else if(stampNumber == 2) {
                when(stampComplete){
                    0->{
                        setStampNo(binding.imagebuttonSecondFirst)
                        setStampNo(binding.imagebuttonSecondSecond)
                    }
                    1->{
                        setStampYes(binding.imagebuttonSecondFirst)
                        setStampNo(binding.imagebuttonSecondSecond)
                    }
                    2->{
                        setStampYes(binding.imagebuttonSecondFirst)
                        setStampYes(binding.imagebuttonSecondSecond)
                    }
                }

                binding.constraintlayoutSubFirst.visibility = View.GONE
                binding.constraintlayoutSubSecond.visibility = View.VISIBLE
                binding.constraintlayoutSubThird.visibility = View.GONE

                height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    120F,
                    resources.displayMetrics
                ).toInt()
                width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    120F,
                    resources.displayMetrics
                ).toInt()

                height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    140F,
                    resources.displayMetrics
                ).toInt()
                width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    120F,
                    resources.displayMetrics
                ).toInt()


                // 스탬프 누르면 인증 팝업 등장
                if(challengeStatus == 1) {
                    binding.imagebuttonSecondFirst.setOnClickListener {
                        checkMission(binding.imagebuttonSecondFirst)
                    }
                    binding.imagebuttonSecondSecond.setOnClickListener {
                        checkMission(binding.imagebuttonSecondSecond)
                    }
                }
            }
            else if(stampNumber == 3) {
                when(stampComplete){
                    0->{
                        setStampNo(binding.imagebuttonThirdFirst)
                        setStampNo(binding.imagebuttonThirdSecond)
                        setStampNo(binding.imagebuttonThirdThird)
                    }
                    1->{
                        setStampYes(binding.imagebuttonThirdFirst)
                        setStampNo(binding.imagebuttonThirdSecond)
                        setStampNo(binding.imagebuttonThirdThird)
                    }
                    2->{
                        setStampYes(binding.imagebuttonThirdFirst)
                        setStampYes(binding.imagebuttonThirdSecond)
                        setStampNo(binding.imagebuttonThirdThird)
                    }
                    3->{
                        setStampYes(binding.imagebuttonThirdFirst)
                        setStampYes(binding.imagebuttonThirdSecond)
                        setStampYes(binding.imagebuttonThirdThird)
                    }
                }

                binding.constraintlayoutSubFirst.visibility = View.GONE
                binding.constraintlayoutSubSecond.visibility = View.GONE
                binding.constraintlayoutSubThird.visibility = View.VISIBLE

                height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    104F,
                    resources.displayMetrics
                ).toInt()
                width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    104F,
                    resources.displayMetrics
                ).toInt()

                height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    124F,
                    resources.displayMetrics
                ).toInt()
                width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    106F,
                    resources.displayMetrics
                ).toInt()

                // 스탬프 누르면 인증 팝업 등장
                if(challengeStatus == 1) {
                    binding.imagebuttonThirdFirst.setOnClickListener {
                        checkMission(binding.imagebuttonThirdFirst)
                    }
                    binding.imagebuttonThirdSecond.setOnClickListener {
                        checkMission(binding.imagebuttonThirdSecond)
                    }
                    binding.imagebuttonThirdThird.setOnClickListener {
                        checkMission(binding.imagebuttonThirdThird)
                    }
                }
            }
            binding.imageviewChallengeJourney.layoutParams.height = height
            binding.imageviewChallengeJourney.layoutParams.width = width
            binding.imageviewChallengeJourney.requestLayout()
        }
        else{
            binding.constraintlayoutChallengeGone.visibility = View.VISIBLE
            binding.constraintlayoutChallengeOngoing.visibility = View.GONE
        }
    }

    // 버튼 이벤트
    fun setButtonEvent(){
        // 챌린지 시작 전 코스 라이브러리로 이동하는 버튼
        binding.buttonChallengeCourse.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_libraryFragment)
        }

        // 챌린지 진행 중 현재 진행중인 코스로 이동하는 버튼
        binding.imagebuttonChallengeCourse.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_courseFragment)
        }

        // 챌린지 진행 중 코스 라이브러리로 이동하는 버튼
        binding.imagebuttonChallengeBrowse.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_libraryFragment)
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChallengeBinding {
        return FragmentChallengeBinding.inflate(inflater, container, false)
    }

    fun setRetrofit(courseId: Int) {
        RetrofitService.challengeService.getChallengeData(
            userJwt,
            //"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiZXA0UmhZcmJUSE9uaHpBUldOVFNTMTpBUEE5MWJIS1pGdkJuUkV1dEEtYzQxSmN6dDBITzVJQkNyMFhzM0VadjFFcUZSVl9jY05semtDbFQtaWxmT3FGTUFWTmFPUFYxaVhIQjIybHhrcHZJRWNTNW4tMjQtZzY2SVR1d0o1aW9aWlJtYVd5R1Q3XzZiUDhlR1BOZHd2SkNwUWxZb1daQlhHVCJ9LCJpYXQiOjE2MjYwODk5OTZ9.fZoVLz1W-C9RNklV0ZPx6yZeysJWfiuOOPhoAlMtG5k",
            courseId
        ).enqueue(object : Callback<ResponseChallengeData> {
            override fun onFailure(call: Call<ResponseChallengeData>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<ResponseChallengeData>,
                response: Response<ResponseChallengeData>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (true) {
                        Log.d("서버 성공", "Challenge 성공" + userCourseStatus)
                        Log.d(
                            "서버", response.body()!!.toString()
                        )

                        for (i in 0 until response.body()!!.data!!.course!!.challenges.size) {
                            if (response.body()!!.data!!.course!!.challenges[i].situation != 0) {
                                stampNumber =
                                    response.body()!!.data!!.course!!.challenges[i].totalStamp
                                stampComplete =
                                    response.body()!!.data!!.course!!.challenges[i].currentStamp
                                courseNumber = response.body()!!.data!!.course!!.totalDays
                                courseDays = response.body()!!.data!!.course!!.challenges[i].id
                                userCourseId = response.body()!!.data!!.course!!.id
                                userChallengeId = response.body()!!.data!!.course!!.challenges[i].id
                                journeyMsg =
                                    response.body()!!.data!!.course!!.challenges[i].description
                                journeyEndMsg =
                                    response.body()!!.data!!.course!!.challenges[i].successDescription
                                challengeType = response.body()!!.data!!.course!!.property
                                userMents = response.body()!!.data!!.course!!.challenges[i].userMents
                                challengeStatus = response.body()!!.data!!.course!!.challenges[i].situation

                                binding.textviewFirstTitle.text = response.body()!!.data!!.course!!.challenges[i].title
                                binding.textviewSecondTitle.text = response.body()!!.data!!.course!!.challenges[i].title
                                binding.textviewThirdTitle.text = response.body()!!.data!!.course!!.challenges[i].title

                                binding.textviewFirstContent.text = response.body()!!.data!!.course!!.title
                                binding.textviewSecondContent.text = response.body()!!.data!!.course!!.title
                                binding.textviewThirdContent.text = response.body()!!.data!!.course!!.title
                                break
                            }
                        }

                        selectChallengeView()
                    }
                } else {
                    Log.d("서버 실패" + userCourseStatus, "${response.body()}")
                    Toast.makeText(
                        context,
                        "만료된 토큰입니다. 우리 아기 고앵이 토큰 하나 더 받아와 쪽-",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    fun setRetrofitMain(){
        org.journey.android.main.model.RetrofitService.mainService.getMainData(
            userJwt
        ).enqueue(object : Callback<ResponseMainModelItem> {

            override fun onFailure(call: Call<ResponseMainModelItem>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<ResponseMainModelItem>,
                response: Response<ResponseMainModelItem>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (true) {
                        Log.d("서버 성공", "Home 성공")
                        Log.d(
                            "서버", response.body()!!.data.toString()
                        )
                        if(response.body()!!.data!!.course!= null){
                            setRetrofit(response.body()!!.data!!.course!!.id)
                            userCourseId = response.body()!!.data!!.course!!.id
                            hasCourse = response.body()!!.data!!.course!!.situation
                        }

                        selectChallengeView()
                        setButtonEvent()
                    }
                }
                else {
                    Log.d("서버 실패", "${response.body()}")
                    Toast.makeText(context, "만료된 토큰입니다. 우리 아기 고앵이 토큰 하나 더 받아와 쪽-", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setStampRetrofit(courseId: Int, challengeId: Int) {
        RetrofitService.challengeService.putChallengeData(
            userJwt,
            courseId,
            challengeId
        ).enqueue(object : Callback<ResponseStampData> {
            override fun onFailure(call: Call<ResponseStampData>, t: Throwable) {
                Log.d("통신 실패", "${t}")
            }

            override fun onResponse(
                call: Call<ResponseStampData>,
                response: Response<ResponseStampData>
            ) {
                // 통신 성공
                if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                    if (true) {
                        Log.d("서버 성공", "Stamp 성공")
                        Log.d(
                            "서버", response.body()!!.toString()
                        )
                    }
                } else {
                    Log.d("서버 실패 "+userCourseId, "${response.body()}")
                    Toast.makeText(
                        context,
                        "해당 id의 코스(챌린지)가 존재하지 않습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun setStampNo(btn: ImageButton){
        when(challengeType){
            0 -> btn.setImageResource(R.drawable.stamp_health_no)
            1 -> btn.setImageResource(R.drawable.stamp_memory_no)
            2 -> btn.setImageResource(R.drawable.stamp_detect_no)
            3 -> btn.setImageResource(R.drawable.stamp_challenge_no)
        }
    }

    private fun setStampYes(btn: ImageButton){
        when(challengeType){
            0 -> btn.setImageResource(R.drawable.stamp_health)
            1 -> btn.setImageResource(R.drawable.stamp_memory)
            2 -> btn.setImageResource(R.drawable.stamp_detect)
            3 -> btn.setImageResource(R.drawable.stamp_challenge)
        }
    }
}