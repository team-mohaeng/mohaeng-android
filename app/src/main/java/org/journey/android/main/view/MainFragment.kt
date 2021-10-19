package org.journey.android.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.journey.android.R
import org.journey.android.databinding.FragmentMainBinding
import org.journey.android.util.AutoClearedValue


class MainFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentMainBinding>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
        showIndexDialog()
    }

    private fun showIndexDialog(){
        with(binding){
            buttonMainReward.setOnClickListener {
                val dialog = DialogIndexExplanationFragment()
                dialog.show(childFragmentManager,tag)
            }
        }
    }

    private fun setClickListener(){
        with(binding){
            buttonMainChat.setOnClickListener {
                findNavController().navigate(R.id.action_frameFragment_to_chatFragment)
            }
            buttonMainFirst.setOnClickListener {
                findNavController().navigate(R.id.action_frameFragment_to_characterFragment)
            }
            buttonMainMypage.setOnClickListener {
                findNavController().navigate(R.id.action_frameFragment_to_myPageFragment)
            }
        }
    }

}