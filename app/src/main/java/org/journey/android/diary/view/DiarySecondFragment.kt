package org.journey.android.diary.view

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentDiarySecondBinding
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
        pressedBack()
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
                val displaymetrics = DisplayMetrics()
                requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
                val height = displaymetrics.heightPixels * 0.4
                val width = displaymetrics.widthPixels * 0.9
                val alertDialog = activity?.let { it1 -> Dialog(it1) }
                val alertDialogInflater : LayoutInflater = LayoutInflater.from(activity)
                val mView : View = alertDialogInflater.inflate(R.layout.diary_hashtag_count_dialog,null)

                val window = alertDialog?.window
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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

            binding.buttonCompelete.setOnClickListener{
                val displaymetricsDiarySecondFragment = DisplayMetrics()
                requireActivity().windowManager.defaultDisplay.getMetrics(displaymetricsDiarySecondFragment)
                val heightDiarySecondDisplay = displaymetricsDiarySecondFragment.heightPixels
                val widthDiarySecondDisplay = displaymetricsDiarySecondFragment.widthPixels * 0.9
                val alertDialogDiarySecondFragment = activity?.let { it2 -> Dialog(it2) }
                val alertDialogDiarySecondFragmentInflater : LayoutInflater = LayoutInflater.from(activity)
                val mView : View = alertDialogDiarySecondFragmentInflater.inflate(R.layout.diary_compelete_dialog,null)

                val windowTwo = alertDialogDiarySecondFragment?.window
                windowTwo?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                if (alertDialogDiarySecondFragment != null)
                {
                    alertDialogDiarySecondFragment.setContentView(mView)
                    alertDialogDiarySecondFragment.create()
                    alertDialogDiarySecondFragment.show()
                    alertDialogDiarySecondFragment.window?.setLayout(widthDiarySecondDisplay.toInt(), heightDiarySecondDisplay.toInt())
                }
            }

            binding.imagebuttonCourseBackSecond.setOnClickListener {
                findNavController().navigate(R.id.action_diarySecondFragment_to_diaryFirstFragment)
            }
    }

    // 갤러리 이미지 첨부
    fun uploadGallery() {
        binding.buttonPictureUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK)
            gallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(gallery, PICK_IMAGE)

        }
    }
    fun pressedBack(){
        binding.imagebuttonCourseBackSecond.setOnClickListener {
            findNavController().popBackStack()
        }}

    companion object {
        private const val PICK_IMAGE = 100
    }



}