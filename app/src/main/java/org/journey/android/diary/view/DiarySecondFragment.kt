package org.journey.android.diary.view

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.internal.Constants
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentDiarySecondBinding
import org.journey.android.diary.view.DiarySecondFragment.Companion.PICK_IMAGE
import org.journey.android.diary.viewmodel.PrivateViewModel
import java.io.InputStream
import java.lang.Exception
import java.util.*

@AndroidEntryPoint
class DiarySecondFragment : BaseFragment<FragmentDiarySecondBinding>() {
    private var imageUri: Uri? = null
    var checkPrivate = false
    private val viewModel by viewModels<PrivateViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiarySecondBinding {
        return FragmentDiarySecondBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadGallery()
        clickButtons()
        binding.buttonCompelete.isEnabled = false

        val user_name = viewModel.getNickname()
        binding.textviewDiaryTextSecond.text = "${user_name}의 오늘을 남겨줘"

        if(moodNum==2)
        {
            binding.imageviewDiaryTodaySecond.setBackgroundResource(R.drawable.ic_feel_third)
        }
        else if(moodNum==1)
        {
            binding.imageviewDiaryTodaySecond.setBackgroundResource(R.drawable.ic_feel_second)
        }
        else if(moodNum==0)
        {
            binding.imageviewDiaryTodaySecond.setBackgroundResource(R.drawable.ic_feel_first)
        }

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
        val secondViewToday = secondNowMonth + "월 " + secondNowDate + "일 "

        binding.textviewNowDateSecond.text = secondViewToday

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
                if (userInput.isNotEmpty()) {
                    binding.buttonCompelete.isSelected = true
                    binding.buttonCompelete.isEnabled = true
                    binding.buttonCompelete.isClickable = true
                }
                else if (userInput.isEmpty()) {
                    binding.buttonCompelete.isSelected = false
                    binding.buttonCompelete.isEnabled = false
                }
            }
        })

        //setRetrofit()

        if(binding.edittextContentHappiness.text.length > 0){
            binding.buttonCompelete.isEnabled = true
            binding.buttonCompelete.isSelected = true
            binding.buttonCompelete.isClickable = true
        }

    }
    
    var path = ""

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        var currentImgUrl: Uri? = uri

        try {
            val bitmap = if(Build.VERSION.SDK_INT>=29){
                val source: ImageDecoder.Source = ImageDecoder.createSource(requireActivity()
                    .contentResolver, currentImgUrl!!)
                ImageDecoder.decodeBitmap(source)
            } else{
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, currentImgUrl!!)
            }

            binding.imageviewDiaryPicture.setImageBitmap(bitmap)
            binding.constraintlayoutPictureUpload.visibility = View.GONE
            binding.imageviewDiaryPicture.visibility = View.VISIBLE
            binding.imagebuttonDiaryPicturex.visibility = View.VISIBLE

        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
    // 갤러리 이미지 첨부
    fun uploadGallery() {
        binding.constraintlayoutPictureUpload.setOnClickListener {
//            val gallery = Intent(Intent.ACTION_PICK)
//            gallery.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            activity?.startActivityForResult(gallery, PICK_IMAGE)
//
//            Log.d("사진사진",gallery.data.toString())
//            path = gallery.data.toString()
//
//            binding.imageviewDiaryPicture.setBackgroundResource(R.drawable.picture_dummy)

            getContent.launch("image/*")
        }

        binding.imagebuttonDiaryPicturex.setOnClickListener {
            binding.constraintlayoutPictureUpload.visibility = View.VISIBLE
            binding.imageviewDiaryPicture.visibility = View.GONE
            binding.imagebuttonDiaryPicturex.visibility = View.GONE
        }
    }

    private fun onActivityResult(requestCode: Int, result: ActivityResult) {
        if(result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                PICK_IMAGE -> {
                    var currentImgUrl: Uri? = intent?.data

                    try {
                        val bitmap = if(Build.VERSION.SDK_INT>=29){
                            val source: ImageDecoder.Source = ImageDecoder.createSource(requireActivity()
                                .contentResolver, currentImgUrl!!)
                            ImageDecoder.decodeBitmap(source)
                        } else{
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, currentImgUrl!!)
                        }

                        binding.constraintlayoutPictureUpload.visibility = View.GONE
                        binding.imageviewDiaryPicture.visibility = View.VISIBLE
                        binding.imageviewDiaryPicture.setImageBitmap(bitmap)
                    }
                    catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    fun clickButtons(){
        binding.imagebuttonDiaryBackSecond.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(R.id.action_frameFragment_to_diaryFirstFragment)

//            findNavController().navigate(R.id.action_diaryFirstFragment_to_diaryFirstFragment)
        }

        binding.imagebuttonDiaryCancelSecond.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
        }

        binding.buttonCompelete.setOnClickListener{
//            findNavController().popBackStack()
//            findNavController().popBackStack()
            findNavController().navigate(R.id.action_diarySecondFragment_to_diaryEndFragment)
        }

        binding.imagebuttonDiaryCheckbox.setOnClickListener {
            if(checkPrivate){
                checkPrivate=false
                binding.imagebuttonDiaryCheckbox.setBackgroundResource(R.drawable.ic_diary_checkbox)
            }
            else {
                checkPrivate=true
                binding.imagebuttonDiaryCheckbox.setBackgroundResource(R.drawable.ic_diary_checkboxno)
            }
        }
        binding.textviewDiaryCheckbox.setOnClickListener {
            if(checkPrivate){
                checkPrivate=false
                binding.imagebuttonDiaryCheckbox.setBackgroundResource(R.drawable.ic_diary_checkbox)
            }
            else {
                checkPrivate=true
                binding.imagebuttonDiaryCheckbox.setBackgroundResource(R.drawable.ic_diary_checkboxno)
            }
        }
    }

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

    private fun uploadPosting(){
        binding.buttonCompelete.setOnClickListener {
//            viewModel.uploadPosting()
        }
    }

    companion object {
        const val PICK_IMAGE = 100
    }

}