package org.journey.android.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.journey.android.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var bottomSheetAdapter: BottomSheetAdapter
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
        bottomSheetAdapter.notifyDataSetChanged()
    }
}

