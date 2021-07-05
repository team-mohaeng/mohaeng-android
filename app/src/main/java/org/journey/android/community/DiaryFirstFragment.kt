package org.journey.android.community

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.journey.android.R
import java.util.*

class DiaryFirstFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val diaryFirstView = inflater.inflate(R.layout.fragment_diary_first, null)
        val firstInstance = Calendar.getInstance()
        val firstNowYear = firstInstance.get(Calendar.YEAR).toString()
        val firstNowMonth = (firstInstance.get(Calendar.MONTH)+1).toString()
        val firstNowDate = firstInstance.get(Calendar.DATE).toString()
        val firstNowDayOfWeek = firstInstance.get(Calendar.DAY_OF_WEEK).toString()
        fun firstNowDayOfWeekToString(x:String?):String{
            when(x)
            {
                "1" -> return "일"
                "2" -> return "월"
                "3" -> return "화"
                "4" -> return "수"
                "5" -> return "목"
                "6" -> return "금"
                "7" -> return "토"
                else -> return ""
            }
        }
        val firstViewToday = firstNowYear + "년 " + firstNowMonth + "월 " + firstNowDate + "일 " + firstNowDayOfWeekToString(firstNowDayOfWeek) +"요일"

        var textviewNowDate = diaryFirstView.findViewById(R.id.textview_now_date) as TextView
        textviewNowDate.setText(firstViewToday)

        //////////////////////////////////////////////////////////////////////////////////////오늘 날짜 , 요일 출력
        val imageButtonFeelOne = diaryFirstView.findViewById(R.id.imagebutton_feel_one) as ImageButton
        val imageButtonFeelTwo = diaryFirstView.findViewById(R.id.imagebutton_feel_two) as ImageButton
        val imageButtonFeelThree = diaryFirstView.findViewById(R.id.imagebutton_feel_three) as ImageButton
        val nextButton = diaryFirstView.findViewById(R.id.button_compelete) as Button
        val textViewBadDay = diaryFirstView.findViewById(R.id.textview_bad_day) as TextView
        val textViewSosoDay = diaryFirstView.findViewById(R.id.textview_soso_day) as TextView
        val textViewGoodDay = diaryFirstView.findViewById(R.id.textview_good_day) as TextView

        textViewBadDay?.setOnClickListener{
            textViewBadDay.isSelected=false
        }

        textViewSosoDay?.setOnClickListener{
            textViewSosoDay.isSelected=false
        }

        textViewGoodDay?.setOnClickListener{
            textViewGoodDay.isSelected=false
        }


        imageButtonFeelOne?.setOnClickListener {
            if(imageButtonFeelTwo.isSelected==true)
                imageButtonFeelTwo.isSelected=false
            if(imageButtonFeelThree.isSelected==true)
                imageButtonFeelThree.isSelected=false
            imageButtonFeelOne.isSelected=true
            nextButton.isSelected=true
            textViewBadDay.isSelected=false
            textViewSosoDay.isSelected=true
            textViewGoodDay.isSelected=true
        }

        imageButtonFeelTwo?.setOnClickListener {
            if(imageButtonFeelOne.isSelected==true)
                imageButtonFeelOne.isSelected=false
            if(imageButtonFeelThree.isSelected==true)
                imageButtonFeelThree.isSelected=false
            imageButtonFeelTwo.isSelected=true
            nextButton.isSelected=true
            textViewBadDay.isSelected=true
            textViewSosoDay.isSelected=false
            textViewGoodDay.isSelected=true
        }

        imageButtonFeelThree?.setOnClickListener {
            if(imageButtonFeelOne.isSelected==true)
                imageButtonFeelOne.isSelected=false
            if(imageButtonFeelTwo.isSelected==true)
                imageButtonFeelTwo.isSelected=false
            imageButtonFeelThree.isSelected=true
            nextButton.isSelected=true
            textViewBadDay.isSelected=true
            textViewSosoDay.isSelected=true
            textViewGoodDay.isSelected=false
        }

        //////////////////////////////////////////////////////////////////////////////////////////눌렀을 때 색상 바뀌게

        return diaryFirstView
    }
}