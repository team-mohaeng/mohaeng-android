package org.journey.android.presentation.main.diary.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.FragmentDiaryFirstBinding
import org.journey.android.presentation.main.diary.viewmodel.PrivateViewModel
import java.util.*

var moodNum = 0

@AndroidEntryPoint
class DiaryFirstFragment : Fragment(){
    private lateinit var  binding : FragmentDiaryFirstBinding
    private val adapter by lazy { DiaryFirstViewPagerAdapter(parentFragmentManager) }
    private val viewModel by viewModels<PrivateViewModel>()

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

        val user_name = viewModel.getNickname()
        binding.textviewDiaryText.text = "${user_name}의 오늘은 어땠어?"


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
        val firstViewToday = firstNowMonth + "월 " + firstNowDate + "일 "

        binding.textviewNowDate.text = firstViewToday

    }

    private fun pressedBack(){
        binding.imagebuttonDiaryBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imagebuttonDiaryCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun clickEvent() {
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