package org.journey.android.diary.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.journey.android.R
import org.journey.android.community.CommunityPostViewModel
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.databinding.FragmentPrivateBinding
import org.journey.android.diary.view.PrivateAdapter
import org.journey.android.diary.dto.PrivateData
import org.journey.android.diary.dto.ResponseDiaryPrivateData
import org.journey.android.login.view.userJwt
import org.journey.android.main.model.RetrofitService
import org.journey.android.util.AutoClearedValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class PrivateFragment : Fragment(){
    private var binding by AutoClearedValue<FragmentPrivateBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentPrivateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pressedBack()
        showMy()

        val privateInstance = Calendar.getInstance()
        val privateNowYear = privateInstance.get(Calendar.YEAR).toString().substring(2, 4)
        var privateNowMonth = (privateInstance.get(Calendar.MONTH) + 1).toString()
        if (privateNowMonth.toInt() < 10)
            privateNowMonth = "0$privateNowMonth"
        val nowSelectedDate = privateNowYear + "년 " + privateNowMonth + "월"

        binding.buttonPrivateTimePicker.text = nowSelectedDate

        val selectDateDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
        val selectDateDialogInflater: LayoutInflater = LayoutInflater.from(activity)
        val selectDateDialogView: View =
            selectDateDialogInflater.inflate(R.layout.private_date_picker, null)

        val selectDialogYear: NumberPicker =
            selectDateDialogView.findViewById(R.id.numberpicker_year)
        val selectDialogMonth: NumberPicker =
            selectDateDialogView.findViewById(R.id.numberpicker_month)
        val selectDialogSave: Button =
            selectDateDialogView.findViewById(R.id.button_date_picker_select)

        val call: Call<ResponseDiaryPrivateData> = RetrofitService.diaryPrivateService
            .getPrivateDiary(
                privateInstance.get(Calendar.YEAR).toString(),
                privateNowMonth,
                userJwt
            )
        /*
        call.enqueue(object : Callback<ResponseDiaryPrivateData> {
            override fun onResponse(
                call: Call<ResponseDiaryPrivateData>,
                responsePrivate: Response<ResponseDiaryPrivateData>
            ) {
                if (responsePrivate.isSuccessful) {
                    val dataPrivate = responsePrivate.body()?.data
                    val countContent = dataPrivate?.myDrawerSmallSatisfactions?.size
                    val privateAdapter = PrivateAdapter()
                    val gridLayoutManager = GridLayoutManager(context, 2)
                    binding.recyclerviewPrivate.layoutManager = gridLayoutManager
                    binding.recyclerviewPrivate.adapter = privateAdapter
                    if (countContent == 0) {
                        binding.imageviewPrivateEmptyImage.isVisible = true
                        binding.textviewPrivateEmptyContent.isVisible = true
                    } else {
                        binding.imageviewPrivateEmptyImage.isVisible = false
                        binding.textviewPrivateEmptyContent.isVisible = false
                        if (dataPrivate != null) {
                            for (i in 0 until countContent!!) {
                                privateAdapter.privateDiaryList.addAll(
                                    listOf<PrivateData>(
                                        PrivateData(
                                            postId = dataPrivate.myDrawerSmallSatisfactions[i].postId,
                                            textViewLikeCount = dataPrivate.myDrawerSmallSatisfactions[i].likeCount.toString(),
                                            textViewPrivateNickName = dataPrivate.myDrawerSmallSatisfactions[i].nickname,
                                            textViewPrivateContent = dataPrivate.myDrawerSmallSatisfactions[i].content,
                                            textViewHashTags = dataPrivate.myDrawerSmallSatisfactions[i].hashtags.joinToString(
                                                " "
                                            ),
                                            imageViewPrivate = dataPrivate.myDrawerSmallSatisfactions[i].mainImage,
                                            hasLike = dataPrivate.myDrawerSmallSatisfactions[i].hasLike
                                        )
                                    )
                                )
                            }
                        }
                    }
                    privateAdapter.notifyDataSetChanged()
                } else {
                    Log.d("ClientTest", "Client Error")
                }
            }

            override fun onFailure(call: Call<ResponseDiaryPrivateData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
*/


        binding.buttonPrivateTimePicker.setOnClickListener()
        {

            selectDialogYear.wrapSelectorWheel = false
            selectDialogMonth.wrapSelectorWheel = false

            selectDialogYear.minValue = 2021
            selectDialogMonth.minValue = 1

            selectDialogYear.maxValue = 2021
            selectDialogMonth.maxValue = 12

            selectDialogSave.setOnClickListener {
                val selected_month = selectDialogMonth.value
                var string_selected_month = selected_month.toString()
                if (selected_month < 10)
                    string_selected_month = "0$selected_month"

                val selected_year_month = (selectDialogYear.value).toString()
                    .substring(2, 4) + "년 " + string_selected_month + "월"
                binding.buttonPrivateTimePicker.text = selected_year_month
                if (selectDateDialog != null) {
                    selectDateDialog.dismiss()
                    selectDateDialog.cancel()
                }

/*
                val call: Call<ResponseDiaryPrivateData> = RetrofitService.diaryPrivateService
                    .getPrivateDiary(
                        selectDialogYear.value.toString(),
                        string_selected_month,
                        userJwt
                    )
                call.enqueue(object : Callback<ResponseDiaryPrivateData> {
                    override fun onResponse(
                        call: Call<ResponseDiaryPrivateData>,
                        responsePrivate: Response<ResponseDiaryPrivateData>
                    ) {
                        if (responsePrivate.isSuccessful) {
                            val dataPrivate = responsePrivate.body()?.data
                            val countContent = dataPrivate?.myDrawerSmallSatisfactions?.size
                            val privateAdapter = PrivateAdapter()
                            val gridLayoutManager = GridLayoutManager(context, 2)
                            binding.recyclerviewPrivate.layoutManager = gridLayoutManager
                            binding.recyclerviewPrivate.adapter = privateAdapter
                            if (countContent == 0) {
                                binding.imageviewPrivateEmptyImage.isVisible = true
                                binding.textviewPrivateEmptyContent.isVisible = true
                            } else {
                                binding.imageviewPrivateEmptyImage.isVisible = false
                                binding.textviewPrivateEmptyContent.isVisible = false
                                if (dataPrivate != null) {
                                    for (i in 0 until countContent!!) {
                                        privateAdapter.privateDiaryList.addAll(
                                            listOf<PrivateData>(
                                                PrivateData(
                                                    postId = dataPrivate.myDrawerSmallSatisfactions[i].postId,
                                                    textViewLikeCount = dataPrivate.myDrawerSmallSatisfactions[i].likeCount.toString(),
                                                    textViewPrivateNickName = dataPrivate.myDrawerSmallSatisfactions[i].nickname,
                                                    textViewPrivateContent = dataPrivate.myDrawerSmallSatisfactions[i].content,
                                                    textViewHashTags = dataPrivate.myDrawerSmallSatisfactions[i].hashtags.joinToString(
                                                        " "
                                                    ),
                                                    imageViewPrivate = dataPrivate.myDrawerSmallSatisfactions[i].mainImage,
                                                    hasLike = dataPrivate.myDrawerSmallSatisfactions[i].hasLike
                                                )
                                            )
                                        )
                                    }
                                }
                            }
                            privateAdapter.notifyDataSetChanged()
                        } else {
                            Log.d("ClientTest", "Client Error")
                        }
                    }

                    override fun onFailure(call: Call<ResponseDiaryPrivateData>, t: Throwable) {
                        Log.d("NetworkTest", "error:$t")
                    }
                })*/
            }

            if (selectDateDialog != null) {
                selectDateDialog.setContentView(selectDateDialogView)
                selectDateDialog.create()
                selectDateDialog.show()
            }
        }

        binding.imagebuttonCommunityBackPrivate.setOnClickListener{
            findNavController().navigate(R.id.action_privateFragment_to_communityFragment)
        }

    }

    fun pressedBack(){
        binding.imagebuttonCommunityBackPrivate.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun showMy(){
        val privateAdapter = PrivateAdapter()
        binding.recyclerviewPrivate.adapter = privateAdapter
        binding.recyclerviewPrivate.layoutManager = LinearLayoutManager(context)

        privateAdapter.privateDiaryList.addAll(
            listOf<PrivateData>(
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "4",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "오늘은 맛집을 다녀왔다",
                    textViewHashTags = "#인스타 #감성 #맛집",
                    imageViewPrivate = "https://i.pinimg.com/474x/a2/4c/28/a24c28e6ca7c68f538273a1971ec0725.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 1,
                    textViewLikeCount = "11",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "오늘은 오래동안 못 본 친구들을 만나서 좋았다",
                    textViewHashTags = "#친구 #서울",
                    imageViewPrivate = "https://i.pinimg.com/474x/75/25/ec/7525ececa051389222a9ddda4ce43413.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 2,
                    textViewLikeCount = "4",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "지하철 환승할 때 기다리지 않아서 좋았다",
                    textViewHashTags = "#지하철 #5호선 #환승",
                    imageViewPrivate = "https://i.pinimg.com/474x/f6/c4/81/f6c4811742ecffce4edf22413dd0172b.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "5",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "오늘은 늦잠을 잘 수 있어서 좋았다",
                    textViewHashTags = "#늦잠 #침대",
                    imageViewPrivate = "https://i.pinimg.com/474x/db/95/30/db95301f192ca29842870c58fc17a765.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "7",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "오늘은 집앞에 있는 돈까스 집에 갔다",
                    textViewHashTags = "#돈까스 #홍대",
                    imageViewPrivate = "https://i.pinimg.com/474x/e1/3e/77/e13e77e9579dbb12d82b2bebf8bb09c4.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "25",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "작업이 예상보다 빨리 끝나서 좋았다",
                    textViewHashTags = "#앱잼 #솝트",
                    imageViewPrivate = "https://i.pinimg.com/474x/c8/39/ec/c839ec689f278c968d6d8ef939667cec.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "76",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "상사에게 칭찬을 받아서 기분이 좋았다",
                    textViewHashTags = "#회사 #상사",
                    imageViewPrivate = "https://i.pinimg.com/474x/3c/15/58/3c1558befa7cbc177579934c32b46527.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "2",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "아버지와 막걸리를 마셔서 기분이 좋았다",
                    textViewHashTags = "#가족 #막걸리 #아버지",
                    imageViewPrivate = "https://i.pinimg.com/474x/ac/c2/d1/acc2d1f133fcd63e5f1f5edcfd656c63.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "1",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "오랜만에 집에 와서 강아지를 봐서 좋았다",
                    textViewHashTags = "#집 #강아지",
                    imageViewPrivate = "https://i.pinimg.com/474x/ab/a8/06/aba806c4633e4387c76375ace1a3f00f.jpg",
                    hasLike = false
                ),
                PrivateData(
                    postId = 0,
                    textViewLikeCount = "0",
                    textViewPrivateNickName = "승민",
                    textViewPrivateContent = "친구들과 놀이동산에 다녀왔다",
                    textViewHashTags = "#놀이동산 #친구",
                    imageViewPrivate = "https://i.pinimg.com/474x/22/31/f0/2231f03d87bda210f8499b5963766ff6.jpg",
                    hasLike = false
                )
            )
        )
        privateAdapter.notifyDataSetChanged()
    }
}