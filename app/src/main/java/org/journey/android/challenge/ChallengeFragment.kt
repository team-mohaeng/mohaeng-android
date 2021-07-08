package org.journey.android.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.journey.android.R
import org.journey.android.databinding.FragmentChallengeBinding

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // 챌린지 시작 여부 알려주는 변수
    private var hasCourse = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChallengeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectChallengeView()
        setButtonEvent()
    }

    // 챌린지 시작 여부에 따라 다른 뷰를 보여줌
    fun selectChallengeView(){
        if(hasCourse){
            binding.constraintlayoutChallengeGone.visibility = View.GONE
            binding.constraintlayoutChallengeOngoing.visibility = View.VISIBLE
        }
        else{
            binding.constraintlayoutChallengeGone.visibility = View.VISIBLE
            binding.constraintlayoutChallengeOngoing.visibility = View.GONE
        }
    }

    // 버튼 이벤트
    fun setButtonEvent(){
        // 챌린지 시작 전 코스 라이브러리로 이동하는 버튼
        binding.buttonChallengeCourse.setOnClickListener {

        }

        // 챌린지 진행 중 스탬프 찍는 버튼 이벤트
        binding.imagebuttonStampFirst.setOnClickListener{
            binding.imagebuttonStampFirst.setImageResource(R.drawable.stamp_selected)
        }
        binding.imagebuttonStampSecond.setOnClickListener {
            binding.imagebuttonStampFirst.setImageResource(R.drawable.stamp_selected)
        }
        binding.imagebuttonStampThird.setOnClickListener{
            binding.imagebuttonStampFirst.setImageResource(R.drawable.stamp_selected)
        }

        // 챌린지 진행 중 현재 진행중인 코스로 이동하는 버튼
        binding.imagebuttonChallengeCourse.setOnClickListener {
            //Navigation.findNavController(binding.root)
            //    .navigate(R.id.course)

        }

        // 챌린지 진행 중 코스 라이브러리로 이동하는 버튼
        binding.imagebuttonChallengeBrowse.setOnClickListener {

        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}