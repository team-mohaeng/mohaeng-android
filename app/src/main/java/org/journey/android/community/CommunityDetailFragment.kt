package org.journey.android.community

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import org.journey.android.R
import org.journey.android.databinding.FragmentCommunityDetailBinding

class CommunityDetailFragment : Fragment() {

    private lateinit var  binding : FragmentCommunityDetailBinding

    // 공감 이모션 리스트
    var likeList : MutableList<String> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentCommunityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClickListener(context)
    }

    private fun setButtonClickListener(ctxt: Context?){
        with(binding){
            buttonCommunityCancel.setOnClickListener { findNavController().popBackStack() }

            buttonCommunityDetailLike.setOnClickListener{
                val mDialogViewEmoji =
                    LayoutInflater.from(ctxt).inflate(R.layout.dialog_myfeed_emoji, null)
                val mBuilderEmoji = AlertDialog.Builder(ctxt)
                    .setView(mDialogViewEmoji)
                val alertDialogEmoji = mBuilderEmoji.create()

                mDialogViewEmoji.setBackgroundColor(Color.TRANSPARENT)
                val windowEmoji = alertDialogEmoji.window
                windowEmoji?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val dialogClose = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_close)
                val dialogFirst = mDialogViewEmoji.findViewById<ImageButton>(R.id.imagebutton_emoji_first)

                dialogClose.setOnClickListener {
                    alertDialogEmoji.dismiss()
                }
                dialogFirst.setOnClickListener {
                    addChipToGroup(3)
                    alertDialogEmoji.dismiss()
                }

                alertDialogEmoji.show()
            }

            imagebuttonCommunityDetailReport.setOnClickListener {
                val reportDialog = activity?.let { it1 -> BottomSheetDialog(it1) }
                val view = layoutInflater.inflate(R.layout.dialog_detail_report, null)
                val window = reportDialog?.window
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val reportBtn = view.findViewById<Button>(R.id.button_dialog_report)

                reportBtn.setOnClickListener {
                    reportDialog?.dismiss()
                }

                reportDialog?.setContentView(view)
                reportDialog?.show()

            }
        }
    }

    fun addChipToGroup(emotion: Int) {
        if (binding.chipgroupLike.childCount < 6) {
            val chip = Chip(context)
            chip.chipBackgroundColor =
                ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow_b))
            chip.chipStrokeColor = ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow))
            chip.chipStrokeWidth = 2F
            chip.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.mohaeng_yellow2)))
            chip.text = emotion.toString()
            chip.textSize = 12F
            chip.chipIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_launcher_background)
            chip.isChipIconVisible = true
            chip.iconStartPadding = 30F
            chip.iconEndPadding = 5F


//            chip.isCloseIconVisible = false
//            chip.closeIcon =
//                ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary_hash_tag_close)
//            chip.closeIconSize = 36F
//            chip.closeIconStartPadding = -10F
//            chip.closeIconEndPadding = 30F
//            chip.closeIconTint =
//                ColorStateList.valueOf(resources.getColor(R.color.journey_pink))
            chip.isClickable = true
            chip.isCheckable = false
            likeList.add(chip.text.toString())
            binding.chipgroupLike.addView(chip as View)
            chip.setOnCloseIconClickListener {
                binding.chipgroupLike.removeView(chip as View)
                likeList.remove(chip.text.toString())
            }
        }
    }

}