package org.journey.android.challenge.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.R
import org.journey.android.databinding.DialogCertifyChallengeBinding
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class ChallengeCertifyDialog(private val listener : CertifyChallengeListener) : DialogFragment() {
    private var binding by AutoClearedValue<DialogCertifyChallengeBinding>()
    interface CertifyChallengeListener {
        fun certifyCourse()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCertifyChallengeBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.selector_index_dialog)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeDialog()
        certifyCourse()
    }

    private fun closeDialog(){
        with(binding){
            buttonDialogClose.setOnClickListener { dismiss() }
        }
    }

    private fun certifyCourse(){
        with(binding){
            buttonDialogA.setOnClickListener { listener.certifyCourse() }
            buttonDialogB.setOnClickListener { listener.certifyCourse() }
            buttonDialogC.setOnClickListener { listener.certifyCourse() }
        }
    }

}