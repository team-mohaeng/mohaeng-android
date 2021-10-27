package org.journey.android.challenge.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import org.journey.android.R
import org.journey.android.databinding.DialogExplanationChallengeBinding
import org.journey.android.util.AutoClearedValue

class ChallengeExplanationDialog : DialogFragment() {
    private var binding by AutoClearedValue<DialogExplanationChallengeBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogExplanationChallengeBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.selector_index_dialog)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeDialog()
    }

    private fun closeDialog(){
        with(binding){
            buttonDialogClose.setOnClickListener { dismiss() }
        }
    }
}
