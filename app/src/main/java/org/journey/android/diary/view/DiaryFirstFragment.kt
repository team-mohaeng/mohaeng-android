package org.journey.android.diary.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentDiaryFirstBinding
import org.journey.android.databinding.FragmentPrivateBinding
import java.util.*

var moodNum = 0

class DiaryFirstFragment : Fragment(){
    private lateinit var  binding : FragmentDiaryFirstBinding
    private val adapter by lazy { DiaryFirstViewPagerAdapter(parentFragmentManager) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        val privateView = inflater.inflate(R.layout.fragment_diary_first, null)

        binding = FragmentDiaryFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvent()
        pressedBack()

        binding.buttonCompelete.isSelected = true

        binding.viewpagerDiaryFirst.adapter = adapter
        binding.viewpagerDiaryFirst.setCurrentItem(0,true)
        binding.dotsIndicatorFeel.setViewPager(binding.viewpagerDiaryFirst)


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
//        val firstViewToday = firstNowYear + "년 " + firstNowMonth + "월 " + firstNowDate + "일 " + firstNowDayOfWeekToString(firstNowDayOfWeek) +"요일"
        val firstViewToday = firstNowMonth + "월 " + firstNowDate + "일 "

        binding.textviewNowDate.text = firstViewToday

//        binding.textviewBadDay.setOnClickListener{
//            binding.textviewBadDay.isSelected=false
//        }
//
//        binding.textviewSosoDay.setOnClickListener{
//            binding.textviewSosoDay.isSelected=false
//        }
//
//        binding.textviewGoodDay.setOnClickListener{
//            binding.textviewGoodDay.isSelected=false
//        }
//
//
//        binding.imagebuttonFeelOne.setOnClickListener {
//            if(binding.imagebuttonFeelTwo.isSelected)
//                binding.imagebuttonFeelTwo.isSelected=false
//            if(binding.imagebuttonFeelThree.isSelected)
//                binding.imagebuttonFeelThree.isSelected=false
//            binding.imagebuttonFeelOne.isSelected=true
//            binding.buttonCompelete.isSelected=true
//            binding.textviewBadDay.isSelected=false
//            binding.textviewSosoDay.isSelected=true
//            binding.textviewGoodDay.isSelected=true
//            moodNum=0
//        }
//
//        binding.imagebuttonFeelTwo.setOnClickListener {
//            if(binding.imagebuttonFeelOne.isSelected)
//                binding.imagebuttonFeelOne.isSelected=false
//            if(binding.imagebuttonFeelThree.isSelected)
//                binding.imagebuttonFeelThree.isSelected=false
//            binding.imagebuttonFeelTwo.isSelected=true
//            binding.buttonCompelete.isSelected=true
//            binding.textviewBadDay.isSelected=true
//            binding.textviewSosoDay.isSelected=false
//            binding.textviewGoodDay.isSelected=true
//            moodNum=1
//        }
//
//        binding.imagebuttonFeelThree.setOnClickListener {
//            if(binding.imagebuttonFeelOne.isSelected)
//                binding.imagebuttonFeelOne.isSelected=false
//            if(binding.imagebuttonFeelTwo.isSelected)
//                binding.imagebuttonFeelTwo.isSelected = false
//            binding.imagebuttonFeelThree.isSelected = true
//            binding.buttonCompelete.isSelected = true
//            binding.textviewBadDay.isSelected = true
//            binding.textviewSosoDay.isSelected = true
//            binding.textviewGoodDay.isSelected = false
//            moodNum=2
//        }
    }

    fun pressedBack(){
        binding.imagebuttonDiaryBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imagebuttonDiaryCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun clickEvent() {
        binding.buttonCompelete.setOnClickListener {
            var diarySecond = DiarySecondFragment()
            var bundle = Bundle()
            bundle.putInt("moodNum",binding.viewpagerDiaryFirst.currentItem)
            diarySecond.arguments = bundle
            Log.d("MOOD", "${binding.viewpagerDiaryFirst.currentItem}, ${diarySecond.arguments}")

            moodNum = binding.viewpagerDiaryFirst.currentItem

            findNavController().navigate(R.id.action_diaryFirstFragment_to_diarySecondFragment)
        }
    }
}