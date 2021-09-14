package org.journey.android.community.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.challenge.challengeStatus
import org.journey.android.community.ResponseCommunityData
import org.journey.android.community.dto.BottomSheetData
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.login.view.userJwt
import org.journey.android.main.model.RetrofitService
import org.journey.android.util.OnSwipeTouchListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {
    lateinit var bottomSheetAdapter : BottomSheetAdapter
    var bottomSheetData = mutableListOf<BottomSheetData>()
    val bottomSheetFragment = BottomSheetFragment()
    var happinessStatus: Int = 0

    var todayUploader = 0
    var sortContent = "date"

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.buttonHappinessWrite.setOnClickListener {
            findNavController().navigate(R.id.action_communityFragment_to_diaryFirstFragment)
        }
        happinessStatus = challengeStatus
        clickEvent()
//        setButtonEvent()
        //setUIListener()
        initRecyclerView()
        setAdapter()
//        dummyData()
        binding.buttonHappinessWrite.setOnClickListener {
            findNavController().navigate(R.id.action_communityFragment_to_diaryFirstFragment)}

        //서버 연결해서 happinessStatus와 몇명이 소확행 피드에 올렸는지 받아옴
        val call: Call<ResponseCommunityData> = RetrofitService.communityService
            .getCommunityDiary(sortContent, userJwt)

        call.enqueue(object: Callback<ResponseCommunityData> {
            override fun onResponse(
                call: Call<ResponseCommunityData>,
                response: Response<ResponseCommunityData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        happinessStatus = data.hasSmallSatisfaction
                        todayUploader = data.userCount
                        binding.textviewHappinessCommunity.text =
                            getString(R.string.total_user_format, todayUploader)
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<ResponseCommunityData>, t: Throwable) {

            }
        })
    }
    private fun initRecyclerView() {
        bottomSheetAdapter =
            BottomSheetAdapter(object  : BottomSheetAdapter.OnItemClickListener{
                override fun itemClickListener(view: View, position: Int) {
                    findNavController().navigate(R.id.action_communityFragment_to_diaryFirstFragment)
                }
            })
    }


    fun setUIListener() {
        with(binding) {
            viewBrowse.setOnTouchListener(
                object : OnSwipeTouchListener(context) {
                    override fun onSwipeUp() {
                        bottomSheetFragment.show(childFragmentManager, "bottomsheet")
                    }
                }
            )
        }
    }

    // 정렬 버튼 이벤트
//    fun setButtonEvent() {
//        binding.textviewUpdate.setOnClickListener{
//            if(binding.textviewUpdate.text == "최신 순"){
//                binding.textviewUpdate.text = "좋아요 순"
//                sortContent = "like"
//            } else {
//                binding.textviewUpdate.text = "최신 순"
//                sortContent = "date"
//            }
//            setAdapter()
//        }

            /*
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
                    findNavController().navigate(R.id.action_communityFragment_to_frameFragment)
                }
            } else if (happinessStatus == 1) {
                dialogTitle.text = "이런, 아직 작성할 수 없어!"
                okButton.text = "오늘의 챌린지로 이동하기"
                dialogContent.text = getString(R.string.happiness_popcontent_ver2)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    //Toast.makeText(this.context, "오늘의 챌린지로 이동하기 클릭", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_communityFragment_to_challengeFragment)
                }
            } else if (happinessStatus == 2) {
                dialogTitle.text = "쟈기, 이미 작성했잖아!"
                okButton.text = "알았어"
                dialogContent.text = getString(R.string.happiness_popcontent_ver3)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "알았어 클릭", Toast.LENGTH_SHORT).show()
                }
            }*/

//        binding.buttonHappinessWrite.setOnClickListener {
//            findNavController().navigate(R.id.action_communityFragment_to_diaryFirstFragment)
//        }
//            // Dialog만들기
//            val mDialogView =
//                LayoutInflater.from(this.context).inflate(R.layout.course_custom_dialog, null)
//            val mBuilder = AlertDialog.Builder(this.context)
//                .setView(mDialogView)
//            val alertDialog = mBuilder.create()
//
//            mDialogView.setBackgroundColor(Color.TRANSPARENT)
//
//            //val  mAlertDialog = mBuilder.show()
//            val window = alertDialog.window
//            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//            val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
//            val dialogButtons =
//                mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
//            val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
//            val dialogContent = mDialogView.findViewById<TextView>(R.id.textview_dialog_content)
//            val dialogImg = mDialogView.findViewById<ImageView>(R.id.imageview_dialog_image)
//
//            dialogButtons.visibility = View.GONE
//            okButton.visibility = View.VISIBLE
//
//            alertDialog.show()
//
//            if (happinessStatus == 0) {
//                dialogTitle.text = "챌린지를 시작해보겠어?"
//                okButton.text = "코스 선택하러 가기"
//                dialogContent.text = getString(R.string.happiness_popcontent_ver1)
//
//                okButton.setOnClickListener {
//                    alertDialog.dismiss()
//                    Toast.makeText(this.context, "코스 선택하러 가기 클릭", Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_communityFragment_to_frameFragment)
//                }
//            } else if (happinessStatus == 1) {
//                dialogTitle.text = "이런, 아직 작성할 수 없어!"
//                okButton.text = "오늘의 챌린지로 이동하기"
//                dialogContent.text = getString(R.string.happiness_popcontent_ver2)
//                dialogImg.setBackgroundResource(R.drawable.graphicyet)
//
//                okButton.setOnClickListener {
//                    alertDialog.dismiss()
//                    //Toast.makeText(this.context, "오늘의 챌린지로 이동하기 클릭", Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_communityFragment_to_challengeFragment)
//                }
//            } else if (happinessStatus == 2) {
//                dialogTitle.text = "쟈기, 이미 작성했잖아!"
//                okButton.text = "알았어"
//                dialogContent.text = getString(R.string.happiness_popcontent_ver3)
//
//                okButton.setOnClickListener {
//                    alertDialog.dismiss()
//                    Toast.makeText(this.context, "알았어 클릭", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

//    }

    private fun setAdapter(){
        val call: Call<ResponseCommunityData> = RetrofitService.communityService
            .getCommunityDiary(sortContent,userJwt)

        call.enqueue(object : Callback<ResponseCommunityData>{
            override fun onResponse(
                call: Call<ResponseCommunityData>,
                response: Response<ResponseCommunityData>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("서버 성공", "community bottom")
                    Log.d("서버 성공", response.body()?.data.toString())
                    val communityData = response.body()?.data
                    binding.recyclerviewCommunityRecord.adapter = bottomSheetAdapter
                    if (communityData != null) {
//                        for(i in 0 until communityData.userCount!!) {
                        for(i in 0 until communityData.community.size) {
                            Log.d("서버 성공", communityData.community[i].toString())
                            bottomSheetAdapter.bottomList.addAll(
                                listOf<BottomSheetData>(
                                    BottomSheetData(
                                        title=communityData.community[i].hashtags.joinToString(" "),
                                        second_tags=communityData.community[i].hashtags.joinToString(""),
                                        diary=communityData.community[i].content,
                                        user_id = communityData.community[i].nickname,
                                        user_prefer = communityData.community[i].likeCount,
                                        has_like = communityData.community[i].hasLike,
                                        main_image = communityData.community[i].mainImage
                                    )
                                )
                            )
                        }
                    }
                    // 데이터 변경되었으니 업데이트해라
                    bottomSheetAdapter.notifyDataSetChanged()

                }
                else{
                    Log.d("Community","Community CE")
                }
            }

            override fun onFailure(call: Call<ResponseCommunityData>, t: Throwable) {
                Log.d("Community","Community NE")
            }

        }
        )
        bottomSheetAdapter.notifyDataSetChanged()
    }

    fun clickEvent() {
        binding.textviewDiary.setOnClickListener {
            findNavController().navigate(R.id.action_frameFragment_to_privateFragment)
        }
    }

    fun dummyData(){
        binding.recyclerviewCommunityRecord.adapter = bottomSheetAdapter

        bottomSheetAdapter.bottomList.addAll(
            listOf<BottomSheetData>(
                BottomSheetData(
                    title = "",
                    second_tags = "",
                    diary = "맛있는피자에시원한맥주먹고선선한날씨에산책했어요맛있는피자에시원한맥",
                    user_id = "시원스쿨",
                    user_prefer = 1,
                    has_like = true,
                    main_image = ""
                )
            )
        )

        // Adapter의 모든 데이터가 변했으니 다시 불러와라
        bottomSheetAdapter.notifyDataSetChanged()
    }
}

