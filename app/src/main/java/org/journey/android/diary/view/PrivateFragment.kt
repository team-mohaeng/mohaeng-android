package org.journey.android.diary.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateBinding
import org.journey.android.diary.dto.PrivateData
import org.journey.android.diary.dto.ResponseDiaryPrivateData
import org.journey.android.diary.service.FeedRequestToServer
import org.journey.android.diary.viewmodel.PrivateViewModel
import org.journey.android.setting.viewmodel.SettingViewModel
import org.journey.android.util.AutoClearedValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

var refreshYear = ""
var refreshMonth = ""

@AndroidEntryPoint
class PrivateFragment : Fragment(){
    private var binding by AutoClearedValue<FragmentPrivateBinding>()
    private val viewModel by viewModels<PrivateViewModel>()
    val privateInstance = Calendar.getInstance()
    val privateNowYear = privateInstance.get(Calendar.YEAR).toString()
    var privateNowMonth = (privateInstance.get(Calendar.MONTH) + 1).toString()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentPrivateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if(refreshCheck == true){
            refreshCheck = false
            findNavController().navigate(R.id.action_privateFragment_to_privateDetailFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pressedBack()
//        showMy()

        setRetrofit(privateInstance.get(Calendar.YEAR).toString(), privateNowMonth)

        if (privateNowMonth.toInt() < 10)
            privateNowMonth = "0$privateNowMonth"
        val nowSelectedDate = privateNowYear + "년 " + privateNowMonth + "월"

        binding.buttonPrivateTimePicker.text = nowSelectedDate + " 안부"

        val selectDateDialog = activity?.let { it1 -> Dialog(it1) }
        val selectDateDialogInflater: LayoutInflater = LayoutInflater.from(activity)
        val selectDateDialogView: View =
            selectDateDialogInflater.inflate(R.layout.private_date_picker, null)

        val selectDialogYear: NumberPicker =
            selectDateDialogView.findViewById(R.id.numberpicker_year)
        val selectDialogMonth: NumberPicker =
            selectDateDialogView.findViewById(R.id.numberpicker_month)
        val selectDialogSave: Button =
            selectDateDialogView.findViewById(R.id.button_date_picker_select)
        val selectDialogCancel: Button =
            selectDateDialogView.findViewById(R.id.button_date_picker_cancel)

        binding.buttonPrivateTimePicker.setOnClickListener()
        {

            selectDialogYear.wrapSelectorWheel = false
            selectDialogMonth.wrapSelectorWheel = false

            selectDialogYear.minValue = 2020
            selectDialogMonth.minValue = 1

            selectDialogYear.maxValue = 2030
            selectDialogMonth.maxValue = 12

            // 기본값 설정
            selectDialogMonth.value = privateNowMonth.toInt()
            selectDialogYear.value = privateNowYear.toInt()

            selectDialogSave.setOnClickListener {
                val selected_month = selectDialogMonth.value
                var string_selected_month = selected_month.toString()
                if (selected_month < 10)
                    string_selected_month = "0$selected_month"

                val selected_year_month = (selectDialogYear.value).toString() + "년 " + string_selected_month + "월 안부"
                binding.buttonPrivateTimePicker.text = selected_year_month
                if (selectDateDialog != null) {
                    selectDateDialog.dismiss()
                    selectDateDialog.cancel()
                }

                setRetrofit((selectDialogYear.value).toString(), string_selected_month)

            }

            selectDialogCancel.setOnClickListener {
                if (selectDateDialog != null) {
                    selectDateDialog.dismiss()
                    selectDateDialog.cancel()
                }
            }

            if (selectDateDialog != null) {
                selectDateDialog.setContentView(selectDateDialogView)
                selectDateDialog.create()
                selectDateDialog.show()
            }
        }

    }

    fun pressedBack(){
        binding.imagebuttonCommunityBackPrivate.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun setRetrofit(year:String, month:String){
        val call: Call<ResponseDiaryPrivateData> = FeedRequestToServer.service
            .getPrivateDiary(
                year,
                month,
                "application/json",
                viewModel.getJWT()
//                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo3N30sImlhdCI6MTYzNDk4MTg1N30.c4ZBhK4vd9AG_LqFyzOfud6x7e_9Flko6_1J098oKsk"
            )

        call.enqueue(object : Callback<ResponseDiaryPrivateData> {
            override fun onResponse(
                call: Call<ResponseDiaryPrivateData>,
                responsePrivate: Response<ResponseDiaryPrivateData>
            ) {
                if (responsePrivate.isSuccessful) {
                    refreshYear = year
                    refreshMonth = month
                    val dataPrivate = responsePrivate.body()?.data
                    val countContent = dataPrivate?.feeds?.size
                    val privateAdapter = PrivateAdapter()
                    binding.recyclerviewPrivate.adapter = privateAdapter
                    if (countContent == 0) {
                        binding.imageviewPrivateEmptyImage.visibility = View.VISIBLE
                        binding.textviewPrivateEmptyContent.visibility = View.VISIBLE
                    } else {
                        binding.imageviewPrivateEmptyImage.visibility = View.GONE
                        binding.textviewPrivateEmptyContent.visibility = View.GONE
                        if (dataPrivate != null) {
                            for (i in 0 until countContent!!) {
                                privateAdapter.privateDiaryList.addAll(
                                    listOf<PrivateData>(
                                        PrivateData(
                                            postId = dataPrivate.feeds[i].postId,
                                            course = dataPrivate.feeds[i].course,
                                            challenge = dataPrivate.feeds[i].challenge,
                                            image = dataPrivate.feeds[i].image,
                                            mood = dataPrivate.feeds[i].mood,
                                            content = dataPrivate.feeds[i].content,
                                            nickname = dataPrivate.feeds[i].nickname,
                                            year = dataPrivate.feeds[i].year,
                                            month = dataPrivate.feeds[i].month,
                                            date = dataPrivate.feeds[i].date,
                                            day = dataPrivate.feeds[i].day,
                                            emoji = dataPrivate.feeds[i].emoji,
                                            myEmoji = dataPrivate.feeds[i].myEmoji,
                                            isReport = dataPrivate.feeds[i].isReport,
                                            isDelete = dataPrivate.feeds[i].isDelete
                                        )
                                    )
                                )
                            }

                            binding.imageviewPrivateEmptyImage.visibility = View.INVISIBLE
                            binding.textviewPrivateEmptyContent.visibility = View.INVISIBLE
                            binding.recyclerviewPrivate.visibility = View.VISIBLE
                        }
                        else{
                            binding.imageviewPrivateEmptyImage.visibility = View.VISIBLE
                            binding.textviewPrivateEmptyContent.visibility = View.VISIBLE
                            binding.recyclerviewPrivate.visibility = View.INVISIBLE
                        }
                    }
                    privateAdapter.notifyDataSetChanged()
                    Log.d("private server 성공", dataPrivate.toString()+"${year} ${month}")

                } else {
                    Log.d("private ClientTest", "Client Error")
                }
            }

            override fun onFailure(call: Call<ResponseDiaryPrivateData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })

    }
/*
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

 */
}