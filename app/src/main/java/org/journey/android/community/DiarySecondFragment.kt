package org.journey.android.community

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.journey.android.R
import java.util.*

class DiarySecondFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val diarySecondView = inflater.inflate(R.layout.fragment_diary_second, null)

        //////////////////////////////////////////////////////////////////////////////////////////////////
        val secondInstance = Calendar.getInstance()
        val secondNowYear = secondInstance.get(Calendar.YEAR).toString()
        val secondNowMonth = (secondInstance.get(Calendar.MONTH)+1).toString()
        val secondNowDate = secondInstance.get(Calendar.DATE).toString()
        val secondNowDayOfWeek = secondInstance.get(Calendar.DAY_OF_WEEK).toString()
        fun secondNowDayOfWeekToString(x:String?):String{
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
        val secondViewToday = secondNowYear + "년 " + secondNowMonth + "월 " + secondNowDate + "일 " + secondNowDayOfWeekToString(secondNowDayOfWeek) +"요일"

        var textviewNowDate = diarySecondView.findViewById(R.id.textview_now_date_second) as TextView
        textviewNowDate.setText(secondViewToday)
        ///////////////////////////////////////////////////////////////////////////////////////////////오늘 날짜 출력

        val edittextUserInputText = diarySecondView.findViewById(R.id.edittext_content_happiness) as EditText
        val textviewCountString = diarySecondView.findViewById(R.id.textview_count_string) as TextView
        val buttonCompelete = diarySecondView.findViewById(R.id.button_compelete) as Button
        edittextUserInputText.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                textviewCountString.text = "0 /40자"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var userInput = edittextUserInputText.text.toString()
                textviewCountString.text = userInput.length.toString() + " /40자"
            }

            override fun afterTextChanged(s: Editable?) {
                var userInput = edittextUserInputText.text.toString()
                textviewCountString.text = userInput.length.toString() + " /40자"
                if(userInput.length>0)
                    buttonCompelete.isSelected=true
                else if(userInput.length==0)
                    buttonCompelete.isSelected=false
            }
        })
        /////////////////////////////////////////////////////////////소확행 글자수 세기 기능 + 글자수가 1글자 이상이면 작성 완료 버튼 활성화

        return diarySecondView
    }
}