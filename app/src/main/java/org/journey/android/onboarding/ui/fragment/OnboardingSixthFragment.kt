package org.journey.android.onboarding.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.journey.android.databinding.FragmentOnboardingSixthBinding
import org.journey.android.frame.MainActivity
import org.journey.android.util.AutoClearedValue

@AndroidEntryPoint
class OnboardingSixthFragment : Fragment() {
    private var binding by AutoClearedValue<FragmentOnboardingSixthBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingSixthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMohaeng()
    }

    private fun startMohaeng(){
        binding.buttonStartMohaeng.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}