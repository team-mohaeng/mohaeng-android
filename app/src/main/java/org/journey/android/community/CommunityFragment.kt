package org.journey.android.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class CommunityFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCommunityBinding>()
    private val viewModel by viewModels<CommunityPostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loadCommunityPost()
        setClickListener()
    }

    private fun loadCommunityPost(){
        binding.recyclerviewCommunityRecord.apply {
            this.adapter = CommunityPostAdapter()
            viewModel.communityPostList.observe(viewLifecycleOwner){
                (adapter as CommunityPostAdapter).posts = it.toMutableList()
            }
        }
    }

    private fun setClickListener(){
        with(binding){
            textviewDiary.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_privateFragment) }
            buttonHappinessWrite.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_privateDetailFragment) }
        }

    }

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
//
//    private fun setAdapter(){
//        val call: Call<ResponseCommunityData> = RetrofitService.communityService
//            .getCommunityDiary(sortContent,userJwt)
//
//        call.enqueue(object : Callback<ResponseCommunityData>{
//            override fun onResponse(
//                call: Call<ResponseCommunityData>,
//                response: Response<ResponseCommunityData>
//            ) {
//                if(response.isSuccessful)
//                {
//                    Log.d("서버 성공", "community bottom")
//                    Log.d("서버 성공", response.body()?.data.toString())
//                    val communityData = response.body()?.data
//                    binding.recyclerviewCommunityRecord.adapter = bottomSheetAdapter
//                    if (communityData != null) {
////                        for(i in 0 until communityData.userCount!!) {
//                        for(i in 0 until communityData.community.size) {
//                            Log.d("서버 성공", communityData.community[i].toString())
//                            bottomSheetAdapter.bottomList.addAll(
//                                listOf<BottomSheetData>(
//                                    BottomSheetData(
//                                        title=communityData.community[i].hashtags.joinToString(" "),
//                                        second_tags=communityData.community[i].hashtags.joinToString(""),
//                                        diary=communityData.community[i].content,
//                                        user_id = communityData.community[i].nickname,
//                                        user_prefer = communityData.community[i].likeCount,
//                                        has_like = communityData.community[i].hasLike,
//                                        main_image = communityData.community[i].mainImage
//                                    )
//                                )
//                            )
//                        }
//                    }
//                    // 데이터 변경되었으니 업데이트해라
//                    bottomSheetAdapter.notifyDataSetChanged()
//
//                }
//                else{
//                    Log.d("Community","Community CE")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseCommunityData>, t: Throwable) {
//                Log.d("Community","Community NE")
//            }
//
//        }
//        )
//        bottomSheetAdapter.notifyDataSetChanged()
//    }
//    private fun loadRetrofitService() {
        //서버 연결해서 happinessStatus와 몇명이 소확행 피드에 올렸는지 받아옴
//    val call: Call<ResponseCommunityData> = RetrofitService.communityService
//        .getCommunityDiary(sortContent, userJwt)
//
//    call.enqueue(object: Callback<ResponseCommunityData> {
//        override fun onResponse(
//            call: Call<ResponseCommunityData>,
//            response: Response<ResponseCommunityData>
//        ) {
//            if (response.isSuccessful) {
//                val data = response.body()?.data
//                if (data != null) {
//                    happinessStatus = data.hasSmallSatisfaction
//                    todayUploader = data.userCount
//                    binding.textviewHappinessCommunity.text =
//                        getString(R.string.total_user_format, todayUploader)
//                }
//            } else {
//
//            }
//        }
//
//        override fun onFailure(call: Call<ResponseCommunityData>, t: Throwable) {
//
//        }
//    })
//}
//    }

}

