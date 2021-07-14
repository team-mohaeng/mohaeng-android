package org.journey.android.community.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.journey.android.community.dto.BottomSheetData
import org.journey.android.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    private val bottomSheetAdapter: BottomSheetAdapter by lazy { BottomSheetAdapter() }
    private var bottomSheetData = mutableListOf<BottomSheetData>()
    private var _binding : FragmentBottomSheetBinding ?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentBottomSheetBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter(){
        binding.recyclerviewCommunityRecord.adapter = bottomSheetAdapter
        listOf<BottomSheetData>(
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "시원스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "주예스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "소정스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "승민스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "주예스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "주예스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "주예스쿨",
                user_prefer = "23"
            ),
            BottomSheetData(
                first_tag = "#맥주",
                second_tag = "#여름",
                diary = "맛있는 피자에 시원한\n맥주 먹고 선선한 날\n씨에 산책했어요.",
                user_id = "주예스쿨",
                user_prefer = "23"
            )
        )
        bottomSheetAdapter.notifyDataSetChanged()
    }
}

