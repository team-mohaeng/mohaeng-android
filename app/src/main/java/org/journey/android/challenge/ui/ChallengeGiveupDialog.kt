package org.journey.android.challenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import org.journey.android.R
import org.journey.android.databinding.DialogGiveupChallengeBinding
import org.journey.android.util.AutoClearedValue

class ChallengeGiveupDialog : DialogFragment() {
    private var binding by AutoClearedValue<DialogGiveupChallengeBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogGiveupChallengeBinding.inflate(inflater, container, false)
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
            buttonCancel.setOnClickListener { dismiss() }
        }
    }
}