package org.journey.android.community.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll
import org.journey.android.R
import org.journey.android.community.viewmodel.CommunityPostViewModel
import org.journey.android.community.ui.adapter.CommunityPostAdapter
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class CommunityFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentCommunityBinding>()
    private val viewModel by viewModels<CommunityPostViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.fetchCommunityPost()
        loadCommunityPost()
        setClickListener()
    }

    private fun loadCommunityPost(){
        binding.recyclerviewCommunityRecord.apply {
            this.adapter = CommunityPostAdapter(object : CommunityPostAdapter.OnItemClickListener {
                override fun itemClick() {
                    findNavController().navigate(R.id.action_frameFragment_to_communityDetailFragment)
                }
            })
            viewModel.communityPostList.observe(viewLifecycleOwner){
                (adapter as CommunityPostAdapter).posts = it.toMutableList()
                (adapter as CommunityPostAdapter).notifyDataSetChanged()
            }
        }
    }

    private fun setClickListener(){
        with(binding){
            textviewDiary.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_privateFragment) }
            buttonHappinessWrite.setOnClickListener { findNavController().navigate(R.id.action_frameFragment_to_diaryFirstFragment) }
        }

    }

}

