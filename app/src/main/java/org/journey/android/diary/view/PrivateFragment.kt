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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateBinding
import org.journey.android.diary.PrivateAdapter
import org.journey.android.diary.dto.PrivateData
import org.journey.android.diary.dto.ResponseDiaryPrivateData
import org.journey.android.login.view.userJwt
import org.journey.android.main.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PrivateFragment : Fragment(){

    private lateinit var  binding : FragmentPrivateBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        val privateView = inflater.inflate(R.layout.fragment_private, null)
        binding = FragmentPrivateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pressedBack()

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
                })
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

}