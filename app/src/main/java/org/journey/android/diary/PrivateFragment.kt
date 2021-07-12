package org.journey.android.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateBinding
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

        val privateInstance = Calendar.getInstance()
        val privateNowYear = privateInstance.get(Calendar.YEAR).toString().substring(2,4)
        var privateNowMonth = (privateInstance.get(Calendar.MONTH)+1).toString()
        if(privateNowMonth.toInt()<10)
            privateNowMonth="0$privateNowMonth"
        val nowSelectedDate = privateNowYear + "년 " + privateNowMonth+"월"

        binding.buttonPrivateTimePicker.text = nowSelectedDate

        binding.buttonPrivateTimePicker.setOnClickListener()
        {
            val selectDateDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
            val selectDateDialogInflater : LayoutInflater = LayoutInflater.from(activity)
            val selectDateDialogView : View = selectDateDialogInflater.inflate(R.layout.private_date_picker,null)

            val selectDialogYear : NumberPicker = selectDateDialogView.findViewById(R.id.numberpicker_year)
            val selectDialogMonth : NumberPicker = selectDateDialogView.findViewById(R.id.numberpicker_month)
            val selectDialogSave : Button = selectDateDialogView.findViewById(R.id.button_date_picker_select)

            selectDialogYear.wrapSelectorWheel = false
            selectDialogMonth.wrapSelectorWheel = false

            selectDialogYear.minValue=2021
            selectDialogMonth.minValue=1

            selectDialogYear.maxValue=2021
            selectDialogMonth.maxValue=12

            selectDialogSave.setOnClickListener{

                val selected_month = selectDialogMonth.value
                var string_selected_month = selected_month.toString()
                if(selected_month<10)
                    string_selected_month = "0$selected_month"

                val selected_year_month = (selectDialogYear.value).toString().substring(2,4)+"년 "+string_selected_month+"월"
                binding.buttonPrivateTimePicker.text = selected_year_month
                if (selectDateDialog != null)
                {selectDateDialog.dismiss()
                    selectDateDialog.cancel()}
            }

            if (selectDateDialog != null) {
                selectDateDialog.setContentView(selectDateDialogView)
                selectDateDialog.create()
                selectDateDialog.show()
            }
        }


        val privateAdapter = PrivateAdapter()

        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.recyclerviewPrivate.layoutManager = gridLayoutManager
        binding.recyclerviewPrivate.adapter=privateAdapter


        privateAdapter.privateDiaryList.addAll(
            listOf<PrivateData>(
                PrivateData(
                    textViewHashTagOne = "#맥주",
                    textViewHashTagTwo = "#여름",
                    textViewLikeCount = "53",
                    textViewPrivateContent = "여름이었다...여름이었다...여름이었다...여름이었다...",
                    textViewPrivateNickName = "시원스쿨",
                    imageViewPrivate = "안뇽하세요"
                ),
                PrivateData(
                    textViewHashTagOne = "#소주",
                    textViewHashTagTwo = "#여름",
                    textViewLikeCount = "53",
                    textViewPrivateContent = "여름이었다...여름이었다...여름이었다...여름이었다...",
                    textViewPrivateNickName = "시원스쿨",
                    imageViewPrivate = "안뇽하세요"
                ),
                PrivateData(
                    textViewHashTagOne = "#양주",
                    textViewHashTagTwo = "#여름",
                    textViewLikeCount = "53",
                    textViewPrivateContent = "여름이었다...여름이었다...여름이었다...여름이었다...",
                    textViewPrivateNickName = "시원스쿨",
                    imageViewPrivate = "안뇽하세요"
                ),
                PrivateData(
                    textViewHashTagOne = "#동동주",
                    textViewHashTagTwo = "#여름",
                    textViewLikeCount = "53",
                    textViewPrivateContent = "여름이었다...여름이었다...여름이었다...여름이었다...",
                    textViewPrivateNickName = "시원스쿨",
                    imageViewPrivate = "안뇽하세요"
                )
            )
        )

        if(privateAdapter.itemCount==0)
        {
            binding.imageviewPrivateEmptyImage.isVisible=true
            binding.textviewPrivateEmptyContent.isVisible=true
        }
        privateAdapter.notifyDataSetChanged()
    }

}