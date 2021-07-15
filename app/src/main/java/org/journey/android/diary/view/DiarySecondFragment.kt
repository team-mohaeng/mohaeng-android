package org.journey.android.diary.view

import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentDiarySecondBinding
import org.journey.android.diary.dto.RequestDiaryWriteData
import org.journey.android.diary.dto.ResponseDiaryWriteData
import org.journey.android.login.view.userJwt
import org.journey.android.main.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*

class DiarySecondFragment : BaseFragment<FragmentDiarySecondBinding>() {
    private var imageUri: Uri? = null
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiarySecondBinding {
        return FragmentDiarySecondBinding.inflate(inflater, container, false)
    }

    var hashTagsList : MutableList<String> = mutableListOf()
    var checkPrivate = false

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
                hashTagsList.add(chip.text.toString())
                binding.chipgroupHashtag.addView(chip as View)
                chip.setOnCloseIconClickListener {
                    binding.chipgroupHashtag.removeView(chip as View)
                    hashTagsList.remove(chip.text.toString())
                }
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

        setRetrofit()

        binding.buttonCompelete.setOnClickListener{

            val displaymetricsDiarySecondFragment = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displaymetricsDiarySecondFragment)
            val heightDiarySecondDisplay = displaymetricsDiarySecondFragment.heightPixels
            val widthDiarySecondDisplay = displaymetricsDiarySecondFragment.widthPixels * 0.9
            val alertDialogDiarySecondFragment = activity?.let { it2 -> Dialog(it2) }
            val alertDialogDiarySecondFragmentInflater : LayoutInflater = LayoutInflater.from(activity)
            val mView : View = alertDialogDiarySecondFragmentInflater.inflate(R.layout.diary_compelete_dialog,null)
            val moodImage = mView.findViewById(R.id.imageview_diary_compelete_dialog_top) as ImageView

            if(moodNum==0)
            {
                moodImage.setBackgroundResource(R.drawable.diary_bad_compelete)
            }
            else if(moodNum==1)
            {
                moodImage.setBackgroundResource(R.drawable.diary_soso_compelete)
            }
            else if(moodNum==2)
            {
                moodImage.setBackgroundResource(R.drawable.diary_good_compelete)
            }

            val windowTwo = alertDialogDiarySecondFragment?.window
            windowTwo?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (alertDialogDiarySecondFragment != null)
            {
                alertDialogDiarySecondFragment.setContentView(mView)
                alertDialogDiarySecondFragment.create()
                alertDialogDiarySecondFragment.show()
                alertDialogDiarySecondFragment.window?.setLayout(widthDiarySecondDisplay.toInt(), heightDiarySecondDisplay.toInt())
                val shareButton = mView.findViewById(R.id.button_diary_compelete_share) as Button
                val privateButton = mView.findViewById(R.id.button_diary_compelete_save) as Button
                shareButton.setOnClickListener {
                    checkPrivate=false
                    findNavController().navigate(R.id.action_diarySecondFragment_to_communityFragment)
                    alertDialogDiarySecondFragment.cancel()
                }
                privateButton.setOnClickListener {
                    checkPrivate=true
                    findNavController().navigate(R.id.action_diarySecondFragment_to_communityFragment)
                    alertDialogDiarySecondFragment.cancel()
                }
            }


            binding.imagebuttonCourseBackSecond.setOnClickListener {
                findNavController().navigate(R.id.action_diarySecondFragment_to_diaryFirstFragment)
            }
    }


    }
    var path = ""
    // 갤러리 이미지 첨부
    fun uploadGallery() {
        binding.buttonPictureUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK)
            gallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(gallery, PICK_IMAGE)
            Log.d("사진사진",gallery.data.toString())
            path = gallery.data.toString()
        }
    }
    fun pressedBack(){
        binding.imagebuttonCourseBackSecond.setOnClickListener {
            findNavController().popBackStack()
        }}

    fun setRetrofit(){
        uploadGallery()
/*
        val file = File(path)
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        val uploadFile = MultipartBody.Part.createFormData("mainImage", file.name, requestFile);


        val hashlist : List<String> = listOf("1","2","3")
//                var file = File("/storage/emulated/0/Download/filename.pdf")
//                val requestFile = RequestBody.create("application/pdf".toMediaTypeOrNull(), file)
//                val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val requestDiaryWriteData = RequestDiaryWriteData(
            content = "안녕",
            mainImage = path,
            mood = "1",
            hashtags = hashlist,
            isPrivate = true
        )

        val call: Call<ResponseDiaryWriteData> = RetrofitService.diaryWriteService
            .writeDiary(userJwt, "hi", uploadFile, "1", listOf("1","2","3"), true)
        call.enqueue(object : Callback<ResponseDiaryWriteData>{
            override fun onResponse(
                call: Call<ResponseDiaryWriteData>,
                response: Response<ResponseDiaryWriteData>
            ) {
                if(response.isSuccessful)
                {
                    Log.d("Compelete", "Compelete Success")
                }
                else{
                    Log.d("Compelete", "Compelete Fail")
                }
            }
            override fun onFailure(call: Call<ResponseDiaryWriteData>, t: Throwable) {
                Log.d("Compelete", "$t")
            }

        })

 */
    }

    companion object {
        private const val PICK_IMAGE = 100
    }



}