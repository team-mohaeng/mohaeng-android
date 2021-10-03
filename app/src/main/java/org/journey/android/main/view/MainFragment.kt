package org.journey.android.main.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

//    @SuppressLint("ResourceType")
//    private fun showIndexDialog(){
//        binding.buttonMainReward.setOnClickListener {
//            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_index_explanation,null)
//            dialogView?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialogView.setBackgroundResource(R.drawable.selector_index_dialog)
//            val builder = AlertDialog.Builder(context)
//                .setView(dialogView)
//            builder.show()
//        }
//    }

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