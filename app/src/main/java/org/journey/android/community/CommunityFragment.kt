package org.journey.android.community

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import org.journey.android.R
import org.journey.android.base.BaseFragment
import org.journey.android.databinding.FragmentCommunityBinding
import org.journey.android.util.OnSwipeTouchListener

class CommunityFragment : BaseFragment<FragmentCommunityBinding>() {

    var happinessStatus = 0
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCommunityBinding {
        return FragmentCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        setButtonEvent()
    }

    fun setButtonEvent() {
        binding.buttonHappinessWrite.setOnClickListener {
            // Dialog만들기
            val mDialogView =
                LayoutInflater.from(this.context).inflate(R.layout.course_custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
            val alertDialog = mBuilder.create()

            mDialogView.setBackgroundColor(Color.TRANSPARENT)

            //val  mAlertDialog = mBuilder.show()
            val window = alertDialog.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
            val dialogButtons =
                mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
            val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
            val dialogContent = mDialogView.findViewById<TextView>(R.id.textview_dialog_content)

            dialogButtons.visibility = View.GONE
            okButton.visibility = View.VISIBLE

            alertDialog.show()

            if (happinessStatus == 0) {
                dialogTitle.text = "챌린지를 시작해보겠어?"
                okButton.text = "코스 선택하러 가기"
                dialogContent.text = getString(R.string.happiness_popcontent_ver1)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "코스 선택하러 가기 클릭", Toast.LENGTH_SHORT).show()
                }
            } else if (happinessStatus == 1) {
                dialogTitle.text = "이런, 아직 작성할 수 없어!"
                okButton.text = "오늘의 챌린지로 이동하기"
                dialogContent.text = getString(R.string.happiness_popcontent_ver2)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "오늘의 챌린지로 이동하기 클릭", Toast.LENGTH_SHORT).show()
                }
            } else if (happinessStatus == 2) {
                dialogTitle.text = "쟈기, 이미 작성했잖아!"
                okButton.text = "알았어"
                dialogContent.text = getString(R.string.happiness_popcontent_ver3)

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "알았어 클릭", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

