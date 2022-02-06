package org.journey.android.presentation.main.diary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.journey.android.presentation.base.BaseFragment
import org.journey.android.databinding.FragmentDiaryEndBinding

class DiaryEndFragment : BaseFragment<FragmentDiaryEndBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiaryEndBinding {
        return FragmentDiaryEndBinding.inflate(inflater, container, false)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_diary_end, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButtons()
    }

    fun clickButtons(){
        binding.buttonDiaryend.setOnClickListener {
//            findNavController().navigate(R.id.action_diaryEndFragment_to_communityFragment)
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().popBackStack()
        }
    }


}