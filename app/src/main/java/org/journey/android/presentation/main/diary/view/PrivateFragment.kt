package org.journey.android.presentation.main.diary.view

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
import org.journey.android.presentation.main.diary.dto.PrivateData
import org.journey.android.presentation.main.diary.dto.ResponseDiaryPrivateData
import org.journey.android.presentation.main.diary.service.FeedRequestToServer
import org.journey.android.presentation.main.diary.viewmodel.PrivateViewModel
import org.journey.android.presentation.util.AutoClearedValue
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

    private fun pressedBack(){
        binding.imagebuttonCommunityBackPrivate.setOnClickListener {
            findNavController().popBackStack()
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

}