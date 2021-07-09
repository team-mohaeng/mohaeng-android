package org.journey.android.diary


import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.journey.android.R
import java.util.*

class DiarySecondFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val diarySecondView = inflater.inflate(R.layout.fragment_diary_second, null)

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
        textviewNowDate.text = secondViewToday

        val PERMISSION_REQUEST_CODE = 201
        val REQ_GALLERY = 202

        val selectImageButton = diarySecondView.findViewById(R.id.button_picture_upload) as AppCompatButton
        selectImageButton.setOnClickListener{
            var writePermission = ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            var readPermission = ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                var intent = Intent(Intent.ACTION_PICK)
                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                intent.type = "image/*"
                startActivityForResult(intent, REQ_GALLERY)
            }
        }

        val hashTagChipGroup = diarySecondView.findViewById(R.id.chipgroup_hashtag) as ChipGroup
        val edittextHashTag = diarySecondView.findViewById(R.id.edittext_hashtag) as EditText

        fun addChipToGroup(hashTag: String) {
            if (hashTagChipGroup.childCount < 5) {
                val chip = Chip(context)
                chip.chipBackgroundColor =
                    ColorStateList.valueOf(resources.getColor(R.color.journey_gray_e))
                chip.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.white)))
                chip.text = hashTag
                chip.textSize = 12F
                chip.chipIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_launcher_background)
                chip.isChipIconVisible = false
                chip.isCloseIconVisible = true
                chip.closeIcon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary_hash_tag_close)
                chip.closeIconSize = 36F
                chip.closeIconStartPadding = -10F
                chip.closeIconEndPadding = 30F
                chip.closeIconTint =
                    ColorStateList.valueOf(resources.getColor(R.color.journey_pink))
                chip.isClickable = true
                chip.isCheckable = false
                hashTagChipGroup.addView(chip as View)
                chip.setOnCloseIconClickListener { hashTagChipGroup.removeView(chip as View) }
            }
        }

        edittextHashTag.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edittextHashTag.text.toString().endsWith(" ") == true) {
                    var str = edittextHashTag.toString()
                    str = "#" + edittextHashTag.text.toString()
                    addChipToGroup(str)
                    edittextHashTag.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

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

        return diarySecondView
    }
}