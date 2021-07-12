package org.journey.android.diary

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
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
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentDiarySecondBinding
import org.journey.android.databinding.FragmentPrivateBinding
import org.journey.android.databinding.FragmentPrivateDetailBinding
import java.util.*

class DiarySecondFragment : BaseFragment<FragmentDiarySecondBinding>() {
    private var imageUri: Uri? = null
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiarySecondBinding {
        return FragmentDiarySecondBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val secondInstance = Calendar.getInstance()
        val secondNowYear = secondInstance.get(Calendar.YEAR).toString()
        val secondNowMonth = (secondInstance.get(Calendar.MONTH) + 1).toString()
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

        binding.textviewNowDateSecond.text = secondViewToday

        val PERMISSION_REQUEST_CODE = 201
        val REQ_GALLERY = 202

        binding.buttonPictureUpload.setOnClickListener{
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


        fun addChipToGroup(hashTag: String) {
            if (binding.chipgroupHashtag.childCount < 5) {
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
                binding.chipgroupHashtag.addView(chip as View)
                chip.setOnCloseIconClickListener { binding.chipgroupHashtag.removeView(chip as View) }
            }
            else if(binding.chipgroupHashtag.childCount==5)
            {
                val alertDialog = activity?.let { it1 -> Dialog(it1) }
                val alertDialogInflater : LayoutInflater = LayoutInflater.from(activity)
                val mView : View = alertDialogInflater.inflate(R.layout.diary_hashtag_count_dialog,null)

                val buttonConfirm:Button = mView.findViewById(R.id.button_confirm)
                if (alertDialog != null) {
                    alertDialog.setContentView(mView)
                    alertDialog.create()
                    alertDialog.show()
                }
                buttonConfirm.setOnClickListener{
                    if (alertDialog != null)
                    {alertDialog.dismiss()
                        alertDialog.cancel()}
                }
            }
        }

        binding.edittextHashtag.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.edittextHashtag.text.toString().endsWith(" ")) {
                    var str: String = "#" + binding.edittextHashtag.text.toString()
                    addChipToGroup(str)
                    binding.edittextHashtag.setText("")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.edittextContentHappiness.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.textviewCountString.text = "0"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var userInput = binding.edittextContentHappiness.text.toString()
                binding.textviewCountString.text = userInput.length.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                var userInput = binding.edittextContentHappiness.text.toString()
                binding.textviewCountString.text = userInput.length.toString()
                if (userInput.isNotEmpty())
                    binding.buttonCompelete.isSelected = true
                else if (userInput.isEmpty())
                    binding.buttonCompelete.isSelected = false
            }
        })
        uploadGallery()
    }

    // 갤러리 이미지 첨부
    fun uploadGallery() {
        binding.buttonPictureUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        }
    }

    companion object {
        private const val PICK_IMAGE = 100
    }

}